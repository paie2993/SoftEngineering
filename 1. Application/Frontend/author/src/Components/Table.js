import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import './Table.css'
import { DataGrid } from '@mui/x-data-grid';
import { ThemeProvider, createTheme } from '@mui/material/styles';

export const Table = (props) => {

    const papers = props.content;
    const columns = props.columns;
    const personally_uploaded = props.personally_uploaded;
    
    const [shownContent, setShownContent] = useState([]);


    //to-do: check author
    function filterPapersByAuthor() {
      const result = papers.filter(paper => paper.status === "personally_uploaded");
      return result;
    }

    useEffect(() => {
        if (personally_uploaded === true) {
          const personally_uploaded = filterPapersByAuthor();
          setShownContent(personally_uploaded);
        }
        else {
          setShownContent(papers);
        }
      }, [papers, personally_uploaded])

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
            <ThemeProvider theme={theme}>
                <DataGrid
                rows={shownContent}
                columns={columns}
                pageSize={7}
                rowsPerPageOptions={[7]}
                selectionModel={selection}
                onSelectionModelChange={(newSelection) => {
                    setSelection(newSelection[0]);
                    const item = shownContent.find(el => el.id === newSelection[0])
                    setSelectedItem(item);
                    //enableButtons(item);
                  }}
                />
            </ThemeProvider> 
        </>
    )
}