import {Component, OnInit} from '@angular/core';
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'app-ui';
  constructor(private toastr: ToastrService) {
  }
  ngOnInit() {
    this.toastr.info("This is a test message")
  }
}
