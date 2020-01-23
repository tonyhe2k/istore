import { TestBed } from '@angular/core/testing';

import { HttpIntercepterBasicAuthServiceService } from './http-intercepter-basic-auth-service.service';

describe('HttpIntercepterBasicAuthServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: HttpIntercepterBasicAuthServiceService = TestBed.get(HttpIntercepterBasicAuthServiceService);
    expect(service).toBeTruthy();
  });
});
