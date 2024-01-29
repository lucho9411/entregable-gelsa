import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListRefillsComponent } from './list-refills.component';

describe('ListRefillsComponent', () => {
  let component: ListRefillsComponent;
  let fixture: ComponentFixture<ListRefillsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListRefillsComponent]
    });
    fixture = TestBed.createComponent(ListRefillsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
