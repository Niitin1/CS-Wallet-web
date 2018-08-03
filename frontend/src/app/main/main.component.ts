import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {DataService} from "../data.service";
import {AccountData} from "../accountdata";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: []
})
export class MainComponent implements OnInit {

  step : number = 0; // TODO step = 0

  constructor(public dataService: DataService) { }

  @Output() changeStepEvent = new EventEmitter<number>();

  ngOnInit() {
  }

  receiveStep($event) {
    this.step = $event
  }

  public goToStep(step : number) {
    this.changeStepEvent.emit(step);
    this.dataService.changePrevStep(0);
  }
}
