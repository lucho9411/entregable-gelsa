import { TestBed } from '@angular/core/testing';

import { RefillsService } from './refills.service';

describe('RefillsService', () => {
  let service: RefillsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RefillsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
