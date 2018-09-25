import {Component, OnInit, Output, EventEmitter} from '@angular/core';
import {DataService} from "../data.service";
import {HttpClient} from "@angular/common/http";
import {SmartContractExecuteRequest} from "../domain/smartcontract/fromweb/SmartContractExecuteRequest";
import {GuidGenerator} from "../utils/GuidGenerator";
import {HttpResponseData} from "../domain/HttpResponse";
import {SmartContractData} from "../domain/smartcontract/toweb/SmartContractData";
import {MethodData} from "../domain/smartcontract/toweb/MethodData";
import {Utils} from "../utils/Utils";
import {GenerateSmartContractBytesRequest} from "../domain/GenerateSmartContractBytesRequest";

@Component({
  selector: 'app-smart',
  templateUrl: './smart.component.html',
  styleUrls: ['./smart.component.css']
})
export class SmartComponent implements OnInit {

  step : number = 65;

  smartContractExecuteRequest: SmartContractExecuteRequest;

  generateSmartContractBytesRequest: GenerateSmartContractBytesRequest;

  currentSmartContract : SmartContractData;

  currentMethod: MethodData;

  smartContractInvocationBytesBase58: string;

  signatureBase58: string;

  smartContractAddress: string;

  privateKey:  string = "";

  forgetNewState: boolean = false;

  tweetnacl = require('tweetnacl');

  base58 = require('base-58');

  bufferToUint8array = require('buffer-to-uint8array');

  constructor(
    public dataService : DataService,
    private http: HttpClient,
    private guidGenerator : GuidGenerator,
    private utils: Utils
  ) {}

  ngOnInit() {
    this.dataService.privateKeyObservable.subscribe(
      privateKey => {
        this.privateKey = privateKey
      }
    )
  }

  @Output() changeStepEvent = new EventEmitter<number>();

  goToStep(step : number) {
    this.changeStepEvent.emit(step);
    this.dataService.changePrevStep(6);
  }

  goToPrevStep() {
    this.changeStepEvent.emit(this.dataService.prevStep);
  }

  searchSmartContract() {
    this.refreshSmartContract(this.smartContractAddress);
  }

  onMethodChange(methodValue) {
    this.currentMethod = methodValue;
  }

  signAndExecuteSmartContract() {

    var innerId = Number(Date.now());
    var tranSource = this.dataService.accountData.wallet;
    var tranTarget = this.currentSmartContract.address;
    var tranAmountInt = 0;
    var tranAmountFrac = 0;
    var tranOfferedMaxFeeInt = 0;
    var tranOfferedMaxFeeFraction = 0;
    var currencyCode = 1;
    var userFieldCount = 1;

    var buffer = this.utils.createTransactionBuffer(
      innerId,
      tranSource,
      tranTarget,
      tranAmountInt,
      tranAmountFrac,
      tranOfferedMaxFeeInt,
      tranOfferedMaxFeeFraction,
      currencyCode,
      userFieldCount,
      this.smartContractInvocationBytesBase58
    );

    let signedMessage = this.tweetnacl.sign.detached(this.bufferToUint8array(buffer), this.base58.decode(this.privateKey));
    this.signatureBase58 = this.base58.encode(signedMessage);
    this.smartContractExecuteRequest = new SmartContractExecuteRequest();
    this.smartContractExecuteRequest.smartContractAddress = this.currentSmartContract.address;
    this.smartContractExecuteRequest.smartContractHashState = this.currentSmartContract.hashState;
    this.smartContractExecuteRequest.executionMethod = this.currentMethod.name;
    this.smartContractExecuteRequest.executionMethodParamsVals = [];
    for(let param of this.currentMethod.params) {
      this.smartContractExecuteRequest.executionMethodParamsVals.push(param.value);
    }
    this.smartContractExecuteRequest.transactionInnerId = innerId;
    this.smartContractExecuteRequest.transactionSource = tranSource;
    this.smartContractExecuteRequest.signatureBase58 = this.signatureBase58;
    this.smartContractExecuteRequest.tranFieldsBytesBase58 = this.base58.encode(buffer);
    this.smartContractExecuteRequest.forgetNewState = this.forgetNewState;

    this.executeSmartContract().subscribe(
      data =>  {
        this.dataService.openDialogInfo((data as HttpResponseData).value);
      },
      err => {
        this.dataService.openDialogInfo(err.message);
      }
    );
  }

  onExecute() {

    this.generateSmartContractBytesRequest = new GenerateSmartContractBytesRequest();
    this.generateSmartContractBytesRequest.addressBase58 = this.currentSmartContract.address;
    this.generateSmartContractBytesRequest.methodName = this.currentMethod.name;
    this.generateSmartContractBytesRequest.paramsVals = [];
    for(let param of this.currentMethod.params) {
      this.generateSmartContractBytesRequest.paramsVals.push(param.value);
    }
    this.generateSmartContractBytesRequest.forgetNewState = this.forgetNewState;

    this.generateSmartContractBytes();
  }

  refreshSmartContract(address : string) {
    this.http.get<SmartContractData>(this.dataService.baseUrl + 'smartcontract/' + address).subscribe(
      res => {
        this.currentSmartContract = res;
        if (this.currentSmartContract.sourceCode.methods && this.currentSmartContract.sourceCode.methods.length>0) {
          this.currentMethod = this.currentSmartContract.sourceCode.methods[0];
        }
      },
      err => {
        this.dataService.openDialogInfo(err.message);
      }
    );
  }

  generateSmartContractBytes() {
    this.http.post<HttpResponseData>(this.dataService.baseUrl + 'smartcontract/generateBytes', this.generateSmartContractBytesRequest).subscribe(
      res => {
        this.smartContractInvocationBytesBase58 = res.value;
        this.signAndExecuteSmartContract();
      },
      err => {
        this.dataService.openDialogInfo(err.message);
      }
    );
  }

  executeSmartContract() {
    return this.http.post(this.dataService.baseUrl + 'smartcontract/execute', this.smartContractExecuteRequest)
      .map(response => response)
  }
}
