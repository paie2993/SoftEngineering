import React, { useState, useEffect } from 'react';
import { Sidebar } from "../Components/Sidebar";
import { AllPapersHS } from "../Components/AllPapersHS";
import "./Papers.css";

export const Papers = () => {
  return (
    <React.Fragment>
      <div class="ap-layout">
        <Sidebar class="sidebar"></Sidebar>
        <AllPapersHS class="all_papers"></AllPapersHS>
      </div>
    </React.Fragment>
  );
};
