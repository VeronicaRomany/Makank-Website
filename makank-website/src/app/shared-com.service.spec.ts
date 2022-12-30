import { TestBed } from '@angular/core/testing';

import { SharedComService } from './shared-com.service';

describe('SharedComService', () => {
  let service: SharedComService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SharedComService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
