import { Kanban } from './model/Kanban';
import { KanbasService } from 'src/app/kanbas/kanbas.service';
import { TestBed } from '@angular/core/testing';

import {
  HttpClientTestingModule,
  HttpTestingController,
} from '@angular/common/http/testing';
import { KANBAS_DATA_LIST, LANE_DATA_LIST } from './model/mock-kanbas-list';



fdescribe('KanbasService', () => {
	let service: KanbasService;
    let httpController: HttpTestingController;
	let userId = 1;
	let kanbanId = 1;

	let url = 'http://localhost:8080/api';
	
	  beforeEach(() => {
	    TestBed.configureTestingModule({
	      imports: [HttpClientTestingModule],
	    });
	    service = TestBed.inject(KanbasService);
	    httpController = TestBed.inject(HttpTestingController);
	  });


	it('getKanbas return a list of kanbas and does a get method', () => {
			
			// 1
		  service.getKanbans(userId).subscribe((res) => {
				//2
	      expect(res).toEqual(KANBAS_DATA_LIST);
	    });
	
			//3/api/kanban/1
	    const req = httpController.expectOne({
	      method: 'GET',
	      url: `${url}/kanban/${userId}`,
	    });

			//4
	    req.flush(KANBAS_DATA_LIST);
	  });
	
	  it('removeKanban', () => {
			
		let kanban:Kanban = {
			id: 1,
			title: 'Escape',
			description: 'Phasellus et lectus nec est vulputate semper in cursus metus. Nam eu odio lacus. Etiam elementum elementum enim a tempus. Quisque id pretium metus. Cras malesuada tellus sed urna placerat commodo.',
			select: true,
			userKanbanPermission:[
			  {id:1, users:{id:1,email:'mercedes@escape.com', online:false}, permission:{id:1,rol:"Owner"}},
			  {id:2, users:{id:2,email:'raul@escape.com', online:false}, permission:{id:2,rol:"Editor"}}
			],
			
			swimlanes:LANE_DATA_LIST
		  };
			// 1
		  service.removeKanban(kanban).subscribe((res) => {
				//2
	      expect(res).toEqual(true);
	    });
	
			//3
	    const req = httpController.expectOne({
	      method: 'DELETE',
	      url: `${url}/kanban/${kanban.id}`,
	    });
			//4
	    req.flush(true);
	  });

})
