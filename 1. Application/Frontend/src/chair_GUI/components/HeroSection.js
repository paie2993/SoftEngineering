import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import './HeroSection.css'
import { DataGrid } from '@mui/x-data-grid';
import { ThemeProvider, createTheme } from '@mui/material/styles';
import { useFetch } from '../../useFetch';

export const HeroSection = (props) => {

    const columns = [
        { field: 'id', headerName: 'ID', width: 70 },
        { field: 'title', headerName: 'Paper Name', width: 270 },
        { field: 'abstract_text', headerName: 'Abstract', width: 300 },
        { field: 'status', headerName: 'Status', width: 300}
      ];

      const accepted = props.accepted;
      
      // const rows = [
      //   { id: 1, paperName: 'An amazing paper1', conference: 'An amazing conference1'},
      //   { id: 2, paperName: 'An amazing paper2', conference: 'An amazing conference2'},
      //   { id: 3, paperName: 'An amazing paper3', conference: 'An amazing conference3'},
      //   { id: 4, paperName: 'An amazing paper4', conference: 'An amazing conference4'},
      //   { id: 5, paperName: 'An amazing paper5', conference: 'An amazing conference5'},
      //   { id: 6, paperName: 'An amazing paper6', conference: 'An amazing conference6'},
      //   { id: 7, paperName: 'An amazing paper7', conference: 'An amazing conference7'},
      //   { id: 8, paperName: 'An amazing paper8', conference: 'An amazing conference8'},
      //   { id: 9, paperName: 'An amazing paper9', conference: 'An amazing conference9'},
      //   { id: 10, paperName: 'An amazing paper10', conference: 'An amazing conference10'}
      // ];

      const theme = createTheme({
        typography: {
          allVariants: {
            fontFamily: '"Anek Telugu", sans-serif',
            fontSize: '15px',
            fontColor: '#413f3d',
            // fontFamily: '"Bebas Neue", cursive',
            borderColor: '#d8d0d0'
          },
        },
      });

    const apiURL = "https://mocki.io/v1/b286fae1-d341-4d28-ad65-ad8613c2e064";
    const papers = useFetch(apiURL);
    
    const [shownPapers, setShownPapers] = useState([]);
    const [buttonText, setButtonText] = useState("");

    function filterPapersByStatus() {
      const result = papers.filter(paper => paper.status === "accepted");
      console.log(result);
      return result;
    }

    useEffect(() => {
      if (accepted === true) {
        const acceptedPapers = filterPapersByStatus();
        setShownPapers(acceptedPapers);
        setButtonText("All Papers");
      }
      else {
        setShownPapers(papers);
        setButtonText("Accepted Papers");
        console.log("It is false");
      }
    }, [papers, accepted])

    return (
        <React.Fragment>
        <div className="hero">
            <div className="title">
                Papers
            </div>
            <div className="table-section">
              <div className="table">   
                  <ThemeProvider theme={theme}>
                      <DataGrid
                      rows={shownPapers}
                      columns={columns}
                      pageSize={7}
                      rowsPerPageOptions={[7]}
                      />
                  </ThemeProvider> 
              </div>
              <div className="button-area">
                <button className="button" >
                  <Link to={`${accepted ? '/' : '/accepted'}`} className="noSelect">
                    {buttonText}
                  </Link>
                </button>
                <button className="button">
                <Link to="/paperstatus" className="noSelect">
                  Paper Status
                </Link>
                </button>
                <button className="button">
                <Link to="/" className="noSelect">
                  Assign Accepted Papers
                </Link>
                </button>
              </div>
            </div>
        </div>
        </React.Fragment>
    )
}