import { User } from './../../kanbas/model/Kanbas';
import { Component, OnInit ,Input} from '@angular/core';

@Component({
  selector: 'app-user-select',
  templateUrl: './user-select.component.html',
  styleUrls: ['./user-select.component.css']
})
export class UserSelectComponent implements OnInit {
  @Input() users:User[];
  constructor() { }

  ngOnInit(): void {
  }

}
