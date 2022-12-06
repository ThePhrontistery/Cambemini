import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { of } from 'rxjs';
import { KanbasService } from '../../../kanbas.service';
import { Lane } from '../../../model/Lane';
import { Notes } from '../../../model/Notes';
import { NoteEditComponent } from './note-edit.component';

const mockTask: Notes =  {
  id: 1,
  content: 'Cloud design',
};

const mockLane: Lane = {
  id: 1,
  title: 'To do',
  notes: [
    {
      id: 1,
      content: 'Cloud design',
    },
    {
      id: 2,        
      content:
        'Think and design how clients will interact with notes at the same time',
    },
  ],
}

const data = {
  lane: mockLane,
  task: null
}

const MatDialogMock = {
  open() {
      return {
          afterClosed: () => of(true)
      };
  }
};

const dialogMock = {
  close: () => { }
};

describe('TaskEditComponent', () => {
  let component: NoteEditComponent;
  let fixture: ComponentFixture<NoteEditComponent>;
  let httpMock: HttpTestingController;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule
      ],
      declarations: [ 
        NoteEditComponent,
        
      ],
      providers: [
        KanbasService,
        { provide: MatDialog, useValue: MatDialogMock },
        { provide: MatDialogRef, useValue: dialogMock }, { provide: MAT_DIALOG_DATA, useValue: data }
      ]
      
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NoteEditComponent);
    component = fixture.componentInstance;
    
    httpMock = TestBed.inject(HttpTestingController);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('dialog should close after saving', ()=> {
    let spy = spyOn(component.dialogRef, 'close').and.callThrough();
    component.onSave();
    expect(spy).toHaveBeenCalled();
  });

  it('dialog should close after cancel', ()=> {
    let spy = spyOn(component.dialogRef, 'close').and.callThrough();
    component.onClose();
    expect(spy).toHaveBeenCalled();
  });
});