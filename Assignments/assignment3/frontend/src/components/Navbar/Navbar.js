import React from "react";
import classes from "./Navbar.module.css";
/**
 * Component represents a navbar which appears at the top of the screen,
 * mostly used for style
 */
const navbar = () => {
  return (
    <nav className={classes.Navbar + " navbar navbar-light shadow p-3 mb-5"}>
      <h2>Kijiji Data Analyzer</h2>
    </nav>
  );
};
export default navbar;
