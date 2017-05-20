/**
 * Created by jguidoux on 22/04/2017.
 * javascript file for the autocompletion
 */


(function () {


    var searchBox = document.getElementById('search');
    var resultsDiv = document.getElementById('results');
    var xhr = new XMLHttpRequest();
    var url = 'http://localhost:8080/api/rest/cities/filter?startWith=';

    function getChildNumber(node) {
        return Array.prototype.indexOf.call(node.parentNode.childNodes, node);
    }


    var keys = {
        up: 38,
        down: 40,
        enter: 13,
        escape: 27,
        delete: 8
    };

    console.log(getComputedStyle(resultsDiv).maxHeight)
    var maxResursPaneheight = parseInt(getComputedStyle(resultsDiv).maxHeight, 10);

    var currentSelectedCity;

    searchBox.addEventListener('keydown', function (event) {

        var keyCode = event.keyCode;
        if (keyCode === keys.down) {
            selectNextCity();
            hoverCity();
        }
        if (keyCode === keys.up) {
            selectPreviousCity();
            hoverCity();
        }
        if (keyCode === keys.enter) {
            selectCurrentCity();
        }
        if (keyCode === keys.escape) {
            discardResults();
        }

    });


    searchBox.addEventListener('keyup', function (event) {

        var keyCode = event.keyCode;
        if (keyCode === keys.delete) {
            // var valueActualLength = this.value.length - 1;
            // var actualValue = this.value.substring(0, valueActualLength);
            if (this.value) {
                sendActualValue(this.value);
            } else {
                discardResults();
            }
        }

    });

    function selectPreviousCity() {
        if (!currentSelectedCity) {
            currentSelectedCity = resultsDiv.firstElementChild
        } else {
            currentSelectedCity.classList.remove('selected');
            currentSelectedCity = currentSelectedCity.previousSibling
        }
    }

    function selectNextCity() {
        if (!currentSelectedCity) {
            currentSelectedCity = resultsDiv.firstElementChild
        } else {
            currentSelectedCity.classList.remove('selected');
            currentSelectedCity = currentSelectedCity.nextSibling
        }
    }

    function selectCurrentCity() {
        if (currentSelectedCity) {
            searchBox.value = getCityName(currentSelectedCity);
            currentSelectedCity = null;
        }
        discardResults();
    }

    function discardResults() {
        resultsDiv.style.display = 'none';
        resultsDiv.innerHTML = '';
        resultsDiv.scrollTop = 0;
    }

    function getCityName(cityNode) {
        return cityNode.firstChild.firstChild.textContent;
    }

    function getElelementtotalHeight(node) {
        var paddingTop = parseFloat(getComputedStyle(node).paddingTop);
        var currentHeight = parseFloat(getComputedStyle(node).height);
        var paddingBottom = parseFloat(getComputedStyle(node).paddingBottom);
        return paddingTop + currentHeight + paddingBottom;
    }

    function getHeightPosInParentContainer(node) {

        var height = getElelementtotalHeight(node);
        var tempNode = node;
        while (tempNode.previousSibling) {
            tempNode = tempNode.previousSibling;
            height += getElelementtotalHeight(tempNode);
        }
        return height;
    }

    function hoverCity() {
        if (currentSelectedCity) {
            currentSelectedCity.classList.add('selected');
            // searchBox.value += "<em>aaa</em>";
            // var currentIndexPosInPx = getChildNumber(currentSelectedCity)*20;//$  getHeightPosInParentContainer(currentSelectedCity);
            var currentIndexPosInPx = getHeightPosInParentContainer(currentSelectedCity);
            if (currentIndexPosInPx > maxResursPaneheight) {
                var oldPosition = resultsDiv.scrollTop + maxResursPaneheight;
                var decalPosition = currentIndexPosInPx - oldPosition;
                resultsDiv.scrollTop += decalPosition;
            }
        }
    }


    searchBox.addEventListener("keypress", function (event) {
        var charCode = (typeof event.which === "number") ? event.which : event.keyCode;
        console.log(charCode);
        console.log(String.fromCharCode(charCode));
        var value = encodeURIComponent(searchBox.value + String.fromCharCode(charCode));
        console.log(value);
        sendActualValue(value)
    });

    function sendActualValue(value) {
        if (value) {
            xhr.open('GET', url + value);
            xhr.send(null);
        }
    }

    xhr.addEventListener('load', function () {
        var cities = JSON.parse(xhr.responseText);
        displayResults(cities);
    });

    function displayResults(cities) {
        if (cities) {
            resultsDiv.style.display = 'block';
            resultsDiv.style.top = getElelementtotalHeight(searchBox) + "px";
            resultsDiv.innerHTML = '';
            var nbResponses = cities.length;
            for (var i = 0; i < nbResponses; i++) {
                var newDiv = document.createElement('div');
                var cityDiv = document.createElement('div');
                var popDiv = document.createElement('div');
                var revenuDiv = document.createElement('div');
                newDiv.className = 'city';
                cityDiv.className = 'cityName';
                popDiv.className = 'cityInfo';
                revenuDiv.className = 'cityInfo';
                newDiv.appendChild(cityDiv);
                newDiv.appendChild(popDiv);
                newDiv.appendChild(revenuDiv);
                var actualCity = cities[i]
                cityDiv.innerHTML = actualCity.name;
                popDiv.innerHTML = "Population : " + actualCity.population;
                revenuDiv.innerHTML = "Revenu par ménage : " + actualCity.revenuPerHabitants + " €/an";
                resultsDiv.appendChild(newDiv);
                newDiv.addEventListener('click', selectCity);
            }
        } else {
            discardResults();
        }
    }

    function selectCity() {
        currentSelectedCity = this;
        selectCurrentCity();
    }

})();