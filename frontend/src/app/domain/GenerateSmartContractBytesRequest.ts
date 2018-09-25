export class GenerateSmartContractBytesRequest {
  addressBase58: string;
  methodName: string;
  paramsVals: string[];
  forgetNewState: boolean;
}
