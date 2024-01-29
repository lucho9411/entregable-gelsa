import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RefillsComponent } from './refills.component';

describe('RefillsComponent', () => {
  let component: RefillsComponent;
  let fixture: ComponentFixture<RefillsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RefillsComponent]
    });
    fixture = TestBed.createComponent(RefillsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
