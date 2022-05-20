import React, { useState, useEffect } from "react";
import "./Sidebar.css";
import HomeRoundedIcon from "@mui/icons-material/HomeRounded";
import ArrowRightRoundedIcon from "@mui/icons-material/ArrowRightRounded";
import ExitToAppRoundedIcon from "@mui/icons-material/ExitToAppRounded";

export const Sidebar = () => {
  return (
    <React.Fragment>
      <div class="sidebar-container">
        <a class="home" href="#">
          <HomeRoundedIcon></HomeRoundedIcon>   home
        </a>
        <ul class="options">
          <li>
            <a href="#">
              <i class="fa fa-book"></i>   Conferences
            </a>
          </li>
          <li>
            <a href="#">
              <i class="fa fa-users"></i>   All Papers
            </a>
          </li>
          <li>
            <a href="#">
              <i class="fa fa-picture-o"></i>   Personal Information
            </a>
          </li>
        </ul>

        <a class="logout" href="#">
          <ExitToAppRoundedIcon></ExitToAppRoundedIcon>   logout
        </a>
      </div>
    </React.Fragment>
  );
};
