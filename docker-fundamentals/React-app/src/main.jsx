import React from "react";
import { createRoot } from "react-dom/client";

const app = React.createElement("h1", null, "Hello World from React");

createRoot(document.getElementById("root")).render(app);
