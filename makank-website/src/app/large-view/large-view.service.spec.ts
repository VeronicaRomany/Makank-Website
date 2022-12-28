import { TestBed } from '@angular/core/testing';

import { LargeViewService } from './large-view.service';

describe('LargeViewService', () => {
  let service: LargeViewService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LargeViewService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
