"use strict";

const catalogs = ['四大_BIG4', '咨询_CONSULTING', '大数据_BIG_DATA', 'FLAG', '金融_FINANCE', '快消_CPG'];
const yesNos = ['true', 'false'];
const genders = ['MALE', 'FEMALE'];
const priorities = ['Urgent', 'High', 'Normal', 'Low', 'Take_Your_time'];

const columnTypes = {//type: "numericColumn"
  textColumn: {filter: 'agTextColumnFilter'},
  largeTextColumn: {filter: 'agTextColumnFilter', cellEditor: 'agLargeTextCellEditor'},
  numberColumn: {filter: 'agNumberColumnFilter'},
  "dateColumn": {
    filter: 'agDateColumnFilter', suppressMenu:true
  },
  "dropDownColumn": {
    filter: 'agTextColumnFilter', cellEditor: 'agSelectCellEditor',
  },
  nonEditableColumn: {editable: false},
  hiddenColumn: {hide: true},
};

// a default column definition with properties that get applied to every column
const defaultColDef = {
  // set every column width
  // width: 100,
  // make every column editable
  editable: true,
  // make every column use 'text' filter by default
  filter: 'agTextColumnFilter',
  sortable: true,
  resizable: true,
  autoHeight: true,
  enableCellChangeFlash: true,
  // filter: true
};

function onAddRow() {
  // const newItems = [createNewRowData()];
  const newItems = [{}];
  const res = gridOptions.api.updateRowData({add: newItems, addIndex: 0});
  printResult(res);
}

function onToggleVisible(columnName, value) {
  // we only need to update one grid, as the other is a slave
  gridOptions.columnApi.setColumnVisible(columnName, value);
  gridOptions.api.sizeColumnsToFit();
}

function printResult(res) {
  console.log('printResult---------------------------------------');
  if (res.add) {
    res.add.forEach( function(rowNode) {
      console.log('Added Row Node', rowNode);
    });
  }
  if (res.remove) {
    res.remove.forEach( function(rowNode) {
      console.log('Removed Row Node', rowNode);
    });
  }
  if (res.update) {
    res.update.forEach( function(rowNode) {
      console.log('Updated Row Node', rowNode);
    });
  }
}

function printNullAs_(params) {
  if (params) {
    return params;
  } else {
    return "_";
  }
}

// CRUD - C POST + U PUT
function onRowValueChangedFunction(event) {
  console.log('onRowValueChanged fired!');
  console.log(event);
  console.log('event.rowIndex = ' + event.rowIndex);
  // node.id is the id for the grid
  console.log('event.node.id = ' + event.node.id);
  console.log(event.oldValue + " -> " + event.newValue);
  // console.log(event.node.data);
  console.log(event.data);
  console.log(event.data.id);
  if (typeof event.data.id !== "undefined") {
    console.log("id IS defined.");
    console.log(JSON.stringify(event.data));
    const url = window.location.origin + put2Replace + event.data.id;
    const otherParam = {
      method: "PUT",
      credentials: 'include',
      headers: {
        "Accept": "application/json;charset=UTF-8",
        "Content-Type": "application/json;charset=UTF-8"
      },
      body: JSON.stringify(event.data),
    };
    fetch(url, otherParam)
      .then(function (response) {
        if (response.ok) {
          return response.json();
        }
        throw new Error('Network response was not ok.');
      })
      .then(function (data) {
        console.log(data);
        showSidebarMessage('Updated successfully');
      })
      .catch(function (error) {
        console.log(error);
        alert('error Updating! msg:' + error);
      })
  } else {
    console.log("id undefined.");
    console.log(JSON.stringify(event.data));
    const url = window.location.origin + post2Create;
    const otherParam = {
      method: "POST",
      credentials: 'include',
      headers: {
        "Accept": "application/json;charset=UTF-8",
        "Content-Type": "application/json;charset=UTF-8"
      },
      body: JSON.stringify(event.data),
    };
    fetch(url, otherParam)
      .then(function (response) {
        console.log('POST response = ');
        console.log(response);
        if (response.ok) {
          return response.json();
        }
        throw new Error('Network response was not ok.');
      })
      .then(function (data) {
        console.log('POST responded data = ');
        console.log(data);
        let rowNode = gridOptions.api.getRowNode(event.node.id);
        rowNode.setDataValue('id', data.id);
        showSidebarMessage('Created successfully!');
      })
      .catch(function (error) {
        console.log(error);
        alert('error Creating! msg:' + error);
      })
  }
}

// CRUD - R GET
function initialGET(getAll) {
  // lookup the container we want the Grid to use
  const eGridDiv = document.querySelector('#myGrid');

  // create the grid passing in the div to use together with the columns & data we want to use
  new agGrid.Grid(eGridDiv, gridOptions);

  const url = window.location.origin + getAll;
  const otherParam = {
    method:"GET",
    credentials: 'include',
    headers:{
      "Accept": "application/json;charset=UTF-8",
      "Content-Type":"application/json;charset=UTF-8"
    },
  };
  fetch(url, otherParam)
    .then(function(response) {
      if(response.ok) {
        return response.json();
      }
      throw new Error('Network response was not ok.');
    })
    .then(function(data) {
      console.log(data);
      gridOptions.api.setRowData(data);
      showSidebarMessage('GET data successfully');
    })
    .catch(function (error) {
      console.log(error);
      alert('error Getting! msg:' + error);
    })
}

// CRUD - D DELETE
function onRemoveSelected(deleteOne) {
  if (!confirm('Delete entry?')) {
    console.log('User says NO. Operation aborted !');
    return false;
  }
  const selectedData = gridOptions.api.getSelectedRows();
  console.log(selectedData);
  const idArray = selectedData.map(function (entity) { return entity.id; });
  const json = JSON.stringify(idArray, null, selectedData.length);
  console.log(json);
  for (const oneId of idArray) {
    const url = window.location.origin + deleteOne + oneId;
    const otherParam = {
      method:"DELETE",
      credentials: 'include',
      headers:{
        "Accept": "application/json;charset=UTF-8",
        "Content-Type":"application/json;charset=UTF-8"
      },
      body:JSON.stringify(event.data),
    };
    fetch(url, otherParam)
      .then(function(response) {
        console.log(response);
        const res = gridOptions.api.updateRowData({remove: selectedData});
        printResult(res);
        showSidebarMessage('DELETE data successfully');
      })
      .catch(function (error) {
        console.log(error);
        alert('error Deleting! msg:' + error);
      });
  }
}

function formatNumber(number) {
  // this puts commas into the number eg 1000 goes to 1,000,
  // i pulled this from stack overflow, i have no idea how it works
  return Math.floor(number).toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,");
}

function currencyFormatter(params) {
  return '$' + formatNumber(params.value);
}

/**
 * showSidebarMessage
 * @param message
 */
function showSidebarMessage(message){
  try {
    $.notify( {
        icon: "tim-icons icon-bell-55", message: message
      }
      , {
        type:"info", delay:1000, placement: {
          from: "top", align: "right"
        }
      }
    )
  }
  catch(a) {
    console.log("Notify library is missing, please make sure you have the notifications library added.")
  }
}

// maintain legacy dataTable
function initDataTable($element) {
  $element.DataTable({
    //exporting buttons
    // dom: 'B<"clear">lfrtip',
    // buttons: {
    //   name: 'primary',
    //   buttons: [ 'copy', 'csv', 'excel' ]
    // },
    // Show options 10, 25, 50 and all records:
    "lengthMenu": [
      [10, 25, 50, 100, -1],
      [10, 25, 50, 100, "All"]
    ],
    // 'First', 'Previous', 'Next' and 'Last' buttons, plus page numbers
    "pagingType": "full_numbers",
    responsive: {
      breakpoints: [
        {name: 'desktop', width: Infinity},
        {name: 'tablet-l', width: 1024},
        {name: 'tablet-p', width: 768},
        {name: 'mobile-l', width: 480},
        {name: 'mobile-p', width: 320}
      ]
    },
    // language: {
    //   search: "_INPUT_",
    //   searchPlaceholder: "Search records"
    // },
    fixedHeader: {
      footer: true
    },
    // fixedColumns: {
    //   leftColumns: 1
    // },
    // rowReorder: {
    //   selector: ':last-child'
    // },
    // colReorder: {
    //   realtime: false
    // },
    // keys: {
    //   columns: ':not(:first-child)'
    // },
    // rowGroup: {
    //   dataSrc: 'group'
    // },
    // select: {
    //   items: 'cells',
    //   info: true
    // },
    // continuously loading for big BIG table only.
    // ajax:           '/api/data',
    // scrollY:        200,
    // deferRender:    true,
    // scroller:       true,
  });

  // var table = $('#datatable').DataTable();
  //
  // // Edit record
  // table.on('click', '.edit', function() {
  //   $tr = $(this).closest('tr');
  //
  //   var data = table.row($tr).data();
  //   alert('You press on Row: ' + data[0] + ' ' + data[1] + ' ' + data[2] + '\'s row.');
  // });
  //
  // // Delete a record
  // table.on('click', '.remove', function(e) {
  //   $tr = $(this).closest('tr');
  //   table.row($tr).remove().draw();
  //   e.preventDefault();
  // });
  //
  // //Like record
  // table.on('click', '.like', function() {
  //   alert('You clicked on Like button');
  // });
}
