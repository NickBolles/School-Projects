/*
TO USE:
Go to https://en.wikipedia.org/wiki/List_of_airports_in_the_United_States
Scroll down to the table that lists each state and each city in that state with an airport
right click on the first entry ("Alabama")
and click "inspect element"
it should bring up the dev tools with a tree of the html structure.
look 3 levels up from alabama for the <tbody> element
select the tbody element

copy and paste the following code into the console and click enter!

Save the text file somewhere on your computer
 */
function nbScrapeWikiAirports(tbody){
    var nbc = $(tbody).children();
    nbc.each(function(){
        var that = $(this);
        that.find("td a").each(function(){
            var text = $(this).text();
            var tmp = text.split(" ");
            if (tmp.length ===1){
                nbArray.push(text);
            }
        });
    });
    var newnbArray = [];
    var idx = 0;
    for (var i=0;i<nbArray.length;i++){
        for (var k = 0; k<6;k++){
            var valid = false;
            var rand;
            while(!valid){
                rand = Math.floor(Math.random()*nbArray.length);
                if (rand != i){
                    valid = true;
                }
            }
            newnbArray[idx] = nbArray[i] + " " + nbArray[rand];
            idx++;
        }
    }

//Create a function to create and download the text file
    function download(text, name, type) {
        var a = document.createElement("a");
        var file = new Blob([text], {type: type});
        a.href = URL.createObjectURL(file);
        a.download = name;
        a.click();
    }

    download(newnbArray.join("\r\n"), 'nbMap.txt', 'text/plain');
}
//$0 represents the currently selected element in dev tools. Select the <tbody> element with the data in it
nbScrapeWikiAirports($0);