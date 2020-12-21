import {HttpHeaders} from "@angular/common/http";

export const environment = {
  production: true
};

export const HTTP_CORS_OPTIONS = {
  headers: new HttpHeaders({
    'Access-Control-Allow-Credentials' : 'true',
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Methods': 'GET, POST, PATCH, DELETE, PUT, OPTIONS',
    'Access-Control-Allow-Headers': 'Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With'
  })
};
