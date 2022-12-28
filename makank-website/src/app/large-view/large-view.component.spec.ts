import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LargeViewComponent } from './large-view.component';

describe('LargeViewComponent', () => {
  let component: LargeViewComponent;
  let fixture: ComponentFixture<LargeViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LargeViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LargeViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
