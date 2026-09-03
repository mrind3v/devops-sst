# /// script
# requires-python = ">=3.13"
# ///
# ─── How to run ───
# docker build -t multistage-homework-python . && docker run --rm -p 8082:8080 multistage-homework-python

from http import HTTPStatus
from http.server import BaseHTTPRequestHandler, HTTPServer
from typing import Final

HOST: Final = "0.0.0.0"
PORT: Final = 8080
PAGE: Final = b"<!doctype html><html><body><h1>Hello World from Python deployment</h1></body></html>"


class HelloWorldHandler(BaseHTTPRequestHandler):
    def do_GET(self) -> None:
        self.send_response(HTTPStatus.OK)
        self.send_header("Content-Type", "text/html; charset=utf-8")
        self.send_header("Content-Length", str(len(PAGE)))
        self.end_headers()
        self.wfile.write(PAGE)


def main() -> None:
    with HTTPServer((HOST, PORT), HelloWorldHandler) as server:
        server.serve_forever()


if __name__ == "__main__":
    main()
