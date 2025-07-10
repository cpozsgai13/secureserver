# secureserver
Java secure server skeleton serves as a decent starting point to
creating a secure https server that secure clients can connect.

The secure parts are the keystore.p12 file that exists in
the resources/ directory, and the associated reference in
the application.properties file.

The keystore.p12 file must contain the cert.key and cert.crt files
embedded with a command like:
`openssl pkcs12 -export -inkey <cert>.key -in <cert>.crt -out keystore.p12 -name <cert>`
where <cert> is the base file name for your generated x509 keys on your front end.

The front end is assumed to have generated the x509 keys, like this example:
`openssl req -x509 -nodes -days 36500 -newkey rsa:4096 -keyout <cert>.key -out <cert>.crt  -subj "/CN=localhost"`

The front end must also run in secure mode:
`ng serve --ssl true --ssl-key src/certs/<cert>.key --ssl-cert src/certs/<cert>.crt`

If your angular.json specifies the ssl options, then user can simply run `ng serve`.
Here are the settings:
"serve": {
  "builder": "@angular/build:dev-server",
  "options": {
    "ssl": true,
    "sslKey": "src/certs/<cert>.key",
    "sslCert": "src/certs/<cert>.crt"
  },
  "configurations": {
    "production": {
      "buildTarget": "staged:build:production"
    },
    "development": {
      "buildTarget": "staged:build:development"
    }
  },
  "defaultConfiguration": "development"
}

Note that this was tested using a self signed certificate and tested locally.

