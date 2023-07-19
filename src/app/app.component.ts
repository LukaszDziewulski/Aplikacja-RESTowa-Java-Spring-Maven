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
  code: string;
  firstname: string;
  lastname: string;
  value: number;
  valueDto: ValueDto;

  requests: any[];

  constructor(private http: HttpClient) {}

//   getCurrentCurrencyValue() {
//     const body = {
//       code: this.code,
//       firstname: this.firstname,
//       lastname: this.lastname
//     };

    this.http.post<any>('/api/currencies/get-current-currency-value-command', body)
      .subscribe(data => {
      log.info(data)
        this.valueDto = data.value;
      });
  }

//   getAllRequests() {
//     this.http.get<any[]>('/api/currencies/requests')
//       .subscribe(data => {
//         this.requests = data;
//       });
//   }
}
