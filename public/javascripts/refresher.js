function autoRefresh_div() {
    console.log("Refreshing")
    var selectList = document.getElementById("zip");
    var index = selectList.selectedIndex;
    if(index == 0){
        return;
    }
    var option = selectList.options[index];
    var zip = option.value;
    var unit =$('input[name="unit"]:checked').val();
    $("#weather-info").load("/refresh", {zip: zip, unit: unit});
}

setInterval('autoRefresh_div()', 900000); // refresh div after 15 minutes
