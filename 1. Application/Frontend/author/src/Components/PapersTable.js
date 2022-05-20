import * as React from 'react';
import Paper from '@mui/material/Paper';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TablePagination from '@mui/material/TablePagination';
import TableRow from '@mui/material/TableRow';

const columns = [
  { id: 'id', label: 'Id'},
  { id: 'name', label: 'Name'},
  {
    id: 'status',
    label: 'Status'
  }
];

function createData(id, name, status) {
  return { id, name, status };
}

const rows = [
  createData(1, 'Name', 'status'),
  createData(2, 'Name', 'status'),
  createData(3, 'Name', 'status'),
  createData(4, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(6, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status'),
  createData(5, 'Name', 'status')
];

export default function PapersTable() {
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(10);

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(+event.target.value);
    setPage(0);
  };

  return (
    <Paper sx={{ width: '100%', overflow: 'hidden' }}>
      <TableContainer sx={{ }}>
        <Table stickyHeader aria-label="sticky table">
          <TableHead>
            <TableRow>
              {columns.map((column) => (
                <TableCell
                  key={column.id}
                  align={column.align}
                  style={{  }}
                >
                  {column.label}
                </TableCell>
              ))}
            </TableRow>
          </TableHead>
          <TableBody>
            {rows
              .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
              .map((row) => {
                return (
                  <TableRow hover role="checkbox" tabIndex={-1} key={row.code}>
                    {columns.map((column) => {
                      const value = row[column.id];
                      return (
                        <TableCell key={column.id} align={column.align}>
                          {column.format && typeof value === 'number'
                            ? column.format(value)
                            : value}
                        </TableCell>
                      );
                    })}
                  </TableRow>
                );
              })}
          </TableBody>
        </Table>
      </TableContainer>
      <TablePagination
        rowsPerPageOptions={[10, 25, 100]}
        component="div"
        count={rows.length}
        rowsPerPage={rowsPerPage}
        page={page}
        onPageChange={handleChangePage}
        onRowsPerPageChange={handleChangeRowsPerPage}
      />
    </Paper>
  );
}
