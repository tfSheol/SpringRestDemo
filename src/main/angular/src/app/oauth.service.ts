import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {LocalStorage} from "ng2-localstorage/dist";

class Token {
  token: String = "";
  token_type: String = "";
  username: String = "";
  ttl: number = 0;
  connected: boolean = false;
}

@Injectable()
export class OauthService {
  private url: String = "http://localhost:8080";
  @LocalStorage() private token: Token;
  private headers: HttpHeaders;

  constructor(private http: HttpClient) {
    if (this.token == null) {
      this.token = new Token();
    }
    console.log(this.token.connected);
    this.initHeader();
  }

  private initHeader(): void {
    this.headers = new HttpHeaders()
      .set('Authorization', this.token.token_type + ' ' + this.token.token);
  }

  public login(username: String, password: String): Observable<Token> {
    return this.http.post<Token>(this.url + "/oauth/auth", null, {
      headers: new HttpHeaders()
        .set('Authorization', 'Basic ' + btoa(username + ":" + password))
    });
  }

  public setToken(token: Token): void {
    this.token = token;
    this.token.connected = true;
    this.initHeader();
    console.log(this.token);
    console.log(this.headers);
  }

  public logout() {
    this.token = new Token();
  }

  public getUsername(): String {
    return this.token.username;
  }

  public isConnected(): boolean {
    return this.token.connected;
  }

  public get<T>(path: String): Observable<T> {
    return this.http.get<T>(this.url + "" + path, {
      headers: this.headers
    });
  }

  public post(path: String, body: any): Observable<any> {
    return this.http.post(this.url + "" + path, body, {
      headers: this.headers
    });
  }

  public put(path: String, body: any): Observable<any> {
    return this.http.put(this.url + "" + path, body, {
      headers: this.headers
    });
  }

  public remove(path: String): Observable<any> {
    return this.http.delete(this.url + "" + path, {
      headers: this.headers
    });
  }

  public error(error: Error): void {
    this.logout();
  }
}