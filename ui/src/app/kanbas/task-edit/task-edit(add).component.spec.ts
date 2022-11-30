import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { of } from 'rxjs';
import { KanbasService } from '../kanbas.service';
import { Lane } from '../model/Lane';
import { Task } from '../model/Task';
import { TaskEditComponent } from './task-edit.component';

const mockTask: Task = {
  id: 1,
  title: 'Cloud design',
  description: 'Design of our cloud-based backend',
  laneId: 1,
}
const mockLane: Lane = {
  id: 1,
  title: 'To do',
  color: '',
  order: 0,
  items: [
    {
      id: 1,
      title: 'Cloud design',
      description: 'Design of our cloud-based backend',
      laneId: 1,
    },
    {
      id: 2,
      title: 'Client tasks usability',
      description:
        'Think and design how clients will interact with notes at the same time',
      laneId: 1,
    },
  ],
}
const data = {
  lane: mockLane,
  task: mockTask
}

const MatDialogMock = {
  open() {
      return {
          afterClosed: () => of(true)
      };
  }
};

describe('TaskEditComponent', () => {
  let component: TaskEditComponent;
  let fixture: ComponentFixture<TaskEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule
      ],
      declarations: [ 
        TaskEditComponent,
        
      ],
      providers: [
        KanbasService,
        { provide: MatDialog, useValue: MatDialogMock },
        { provide: MatDialogRef, useValue: {} }, { provide: MAT_DIALOG_DATA, useValue: data }
      ]
      
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TaskEditComponent);
    component = fixture.componentInstance;
    

    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
