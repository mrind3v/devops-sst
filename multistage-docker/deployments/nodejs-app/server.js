const http = require("node:http");

const port = 8080;
const page = "<!doctype html><html><body><h1>Hello World from Node.js deployment</h1></body></html>";

http
  .createServer((_request, response) => {
    response.writeHead(200, { "Content-Type": "text/html; charset=utf-8" });
    response.end(page);
  })
  .listen(port, "0.0.0.0", () => {
    console.log(`Node.js deployment listening on port ${port}`);
  });
