import { TestBed } from '@angular/core/testing';

import { MailInfoService } from './mail-info.service';

describe('MailInfoService', () => {
  let service: MailInfoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MailInfoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
