interface Printer {
	
    void print(String document);
}

interface Scanner {
	
    void scan(String document);
}

class AllInOnePrinter implements Printer, Scanner {
	
    @Override
    public void print(String document) {
        System.out.println("Printing: " + document);
    }

    @Override
    public void scan(String document) {
    	
        System.out.println("Scanning: " + document);
    }
}

class PrinterOnly implements Printer {
    @Override
    public void print(String document) {
        System.out.println("Printing: " + document);
    }
}

class ScannerOnly implements Scanner {
    @Override
    public void scan(String document) {
        System.out.println("Scanning: " + document);
    }
}

public class ISP {
    public static void main(String[] args) {
    	
        Printer printer = new PrinterOnly();
        printer.print("document1.docx");

        Scanner scanner = new ScannerOnly();
        scanner.scan("dokument2.pdf");

        AllInOnePrinter aiop = new AllInOnePrinter();
        aiop.print("document.pdf");
        aiop.scan("dokument.pdf");
        
    }
}
