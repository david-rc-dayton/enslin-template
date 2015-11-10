var n = 0

function helloAction() {
  n += 1
  document.getElementById("helloButton").innerHTML = n

  var xmlHttp = new XMLHttpRequest()
  xmlHttp.open("GET", "/hello/" + n, false)
  xmlHttp.send(null)
  return xmlHttp.responseText
}
