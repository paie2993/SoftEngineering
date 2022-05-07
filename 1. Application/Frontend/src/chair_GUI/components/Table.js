import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import './Table.css'
import { DataGrid } from '@mui/x-data-grid';
import { ThemeProvider, createTheme } from '@mui/material/styles';

export const Table = (props) => {

    const papers = props.content;
    const columns = props.columns;
    const accepted = props.accepted;
    
    const [shownPapers, setShownPapers] = useState([]);

    function filterPapersByStatus() {
      const result = papers.filter(paper => paper.status === "accepted");
      console.log(result);
      return result;
    }

    useEffect(() => {
        if (accepted === true) {
          const acceptedPapers = filterPapersByStatus();
          setShownPapers(acceptedPapers);
        }
        else {
          setShownPapers(papers);
        }
      }, [papers, accepted])

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
    const [selection, setSelection] = React.useState();
    const [selectedItem, setSelectedItem] = props.selected;

    return (
        <>
        <div className="table">   
            <ThemeProvider theme={theme}>
                <DataGrid
                rows={shownPapers}
                columns={columns}
                pageSize={7}
                rowsPerPageOptions={[7]}
                selectionModel={selection}
                onSelectionModelChange={(newSelection) => {
                    setSelection(newSelection[0]);
                    const btn1 = document.getElementById("btn-assign");
                    btn1.disabled = false;
                    const btn2 = document.getElementById("btn-status");
                    btn2.disabled = false;
                    const item = shownPapers.find(el => el.id == newSelection[0])
                    setSelectedItem(item);
                    localStorage.setItem("selectedPaper", JSON.stringify(item));
                  }}
                />
            </ThemeProvider> 
        </div>
        </>
    )
}