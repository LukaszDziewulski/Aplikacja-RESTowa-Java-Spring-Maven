import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

export interface ValueDto {
  value: number;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title: string = 'NBP currency app';
  code: string = '';
  firstname: string = '';
  lastname: string = '';
  value: number = 0;
  requests: any[] | undefined;

  constructor(private http: HttpClient) {}

  getCurrentCurrencyValue() {
    const body = {
      code: this.code,
      firstname: this.firstname,
      lastname: this.lastname
    };

    this.http.post<any>('http://localhost:8080/api/currencies/get-current-currency-value-command', body)
      .subscribe(data => {
        this.value = data.value;
      });
  }

  getAllRequests() {
    this.http.get<any[]>('http://localhost:8080/api/currencies/requests')
      .subscribe(data => {
        this.requests = data;
      });
  }
}
