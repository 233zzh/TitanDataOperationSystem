import { ElementRef, EventEmitter } from '@angular/core';
import 'bootstrap-material-design/dist/js/ripples.min';
import 'bootstrap-material-design/dist/js/material.min.js';
import 'bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js';
import 'bootstrap-material-datetimepicker/css/bootstrap-material-datetimepicker.css';
export declare class DatePickerDirective {
    private elementRef;
    constructor(elementRef: ElementRef);
    private el;
    dtpFormat: string;
    dtpLocale: any;
    dtpTime: boolean;
    dtpDate: boolean;
    datetimepicker: string;
    datepicker: string;
    timepicker: string;
    date: Date;
    dateChange: EventEmitter<any>;
    ngOnInit(): void;
}
