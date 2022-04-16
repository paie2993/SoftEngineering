import React, { useState, useEffect } from 'react';
import './HeroSection.css'
import { DataGrid } from '@mui/x-data-grid';
import { ThemeProvider, createTheme } from '@mui/material/styles';

export const HeroSection = () => {

    const columns = [
        { field: 'id', headerName: 'ID', width: 70 },
        { field: 'paperName', headerName: 'Paper Name', width: 300 },
        { field: 'conference', headerName: 'Conference', width: 300 },
      ];
      
      const rows = [
        { id: 1, paperName: 'An amazing paper1', conference: 'An amazing conference1'},
        { id: 2, paperName: 'An amazing paper2', conference: 'An amazing conference2'},
        { id: 3, paperName: 'An amazing paper3', conference: 'An amazing conference3'},
        { id: 4, paperName: 'An amazing paper4', conference: 'An amazing conference4'},
        { id: 5, paperName: 'An amazing paper5', conference: 'An amazing conference5'},
        { id: 6, paperName: 'An amazing paper6', conference: 'An amazing conference6'},
        { id: 7, paperName: 'An amazing paper7', conference: 'An amazing conference7'},
        { id: 8, paperName: 'An amazing paper8', conference: 'An amazing conference8'},
        { id: 9, paperName: 'An amazing paper9', conference: 'An amazing conference9'},
        { id: 10, paperName: 'An amazing paper10', conference: 'An amazing conference10'}
      ];

      const theme = createTheme({
        typography: {
          allVariants: {
            fontFamily: '"Anek Telugu", sans-serif',
            fontSize: '15px',
            fontColor: '#413f3d',
            fontFamily: '"Bebas Neue", cursive',
            borderColor: '#d8d0d0'
          },
        },
      });

    return (
        <React.Fragment>
        <div class="hero">
            <div class="title">
                Papers
            </div>
            <div class="table-section">
              <div class="table">   
                  <ThemeProvider theme={theme}>
                      <DataGrid
                      rows={rows}
                      columns={columns}
                      pageSize={7}
                      rowsPerPageOptions={[7]}
                      />
                  </ThemeProvider> 
              </div>
              <div class="button-area">
                <button class="button">
                  Accepted Papers
                </button>
                <button class="button">
                  Paper Status
                </button>
                <button class="button">
                  Assign Accepted Papers
                </button>
              </div>
            </div>
        </div>
        </React.Fragment>
    )
}