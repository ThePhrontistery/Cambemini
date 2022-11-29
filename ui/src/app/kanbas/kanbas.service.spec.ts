import { Kanban } from './model/Kanbas';
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
		  service.getKanban().subscribe((res) => {
				//2
	      expect(res).toEqual(KANBAS_DATA_LIST);
	    });
	
			//3
	    const req = httpController.expectOne({
	      method: 'GET',
	      url: `${url}/kanban`,
	    });

			//4
	    req.flush(KANBAS_DATA_LIST);
	  });
	
	  it('removeKanban', () => {
			
		let kanban:Kanban = {
			id: 1,
			title: 'Escape',
			description: 'Phasellus et lectus nec est vulputate semper in cursus metus. Nam eu odio lacus. Etiam elementum elementum enim a tempus. Quisque id pretium metus. Cras malesuada tellus sed urna placerat commodo.',
		
			code: 'mural-0001',
			select: true,
			icon: 'settings_accesibility',
			users: [
			  { email: 'fredy@test.com', initial: 'FHO', online: true },
			  { initial: 'DAV', email: 'david@test.com', online: false },
			],
			swimlanes:LANE_DATA_LIST
		  }
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
