package main

import (
	"encoding/xml"
	"fmt"
	"io/ioutil"
	"os"
	"strings"
)

func main() {
	printEntireXmlDocument()
}

func printEntireXmlDocument() {
	bytes, err := ioutil.ReadFile("../zavrsni_rad.xml")
	checkError(err)
	r := strings.NewReader(string(bytes))

	parser := xml.NewDecoder(r)
	depth := 0
	for {
		token, err := parser.Token()
		if err != nil {
			break
		}
		switch t := token.(type) {
		case xml.StartElement:
			elmt := xml.StartElement(t)
			name := elmt.Name.Local
			attributes := elmt.Attr
			printElmt(name, depth, attributes)
			depth++
		case xml.EndElement:
			depth--
			elmt := xml.EndElement(t)
			name := elmt.Name.Local
			printElmt(name, depth, nil)
		case xml.CharData:
			bytes := xml.CharData(t)
			printElmt(string([]byte(bytes)), depth, nil)
		case xml.Comment:
			printElmt("Comment", depth, nil)
		case xml.ProcInst:
			printElmt("ProcInst", depth, nil)
		case xml.Directive:
			printElmt("Directive", depth, nil)
		default:
			fmt.Println("Unknown")
		}
	}
}

func printElmt(name string, depth int, attributes []xml.Attr) {
	for n := 0; n < depth; n++ {
		fmt.Print("  ")
	}
	fmt.Print(name)
	if attributes != nil && len(attributes) > 0 {
		fmt.Print("	ATTRIBUTES:")
		for _, attr := range attributes {
			fmt.Print(" Name: " + attr.Name.Local + ", Value: " + attr.Value)
		}
	}
}

func checkError(err error) {
	if err != nil {
		fmt.Println("Fatal error ", err.Error())
		os.Exit(1)
	}
}
