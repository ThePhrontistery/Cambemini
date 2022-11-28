
import { Component, OnInit ,Input} from '@angular/core';
import { User } from 'src/app/kanbas/model/User';

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
