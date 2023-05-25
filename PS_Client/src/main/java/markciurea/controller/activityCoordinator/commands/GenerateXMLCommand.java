package markciurea.controller.activityCoordinator.commands;

import markciurea.controller.activityCoordinator.ControllerActivityCoordinator;
import markciurea.controller.helper.ICommand;
import markciurea.model.entities.dto.UserShort;
import markciurea.model.entities.thrashLocation.ThrashLocation;
import markciurea.model.entities.user.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class GenerateXMLCommand implements ICommand {

    private final ControllerActivityCoordinator vm;

    public GenerateXMLCommand(ControllerActivityCoordinator vm) {
        this.vm = vm;
    }

    @Override
    public void execute() {
        String xmlFile = "thrashLocations.xml";
        // TODO
//        List<ThrashLocation> thrashLocationList = ThrashLocationRepository.getInstance().getAllThrashLocations();
        List<ThrashLocation> thrashLocationList = null;

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element rootElement = doc.createElement("thrashLocations");
            doc.appendChild(rootElement);

            for (ThrashLocation thrashLocation : thrashLocationList) {
                Element thrashLocationEl = doc.createElement("thrashLocation");

                // Parse simple fields
                Element idElement = doc.createElement("id");
                idElement.appendChild(doc.createTextNode(String.valueOf(thrashLocation.getId())));

                Element addressNameEl = doc.createElement("address_name");
                addressNameEl.appendChild(doc.createTextNode(String.valueOf(thrashLocation.getAddressName())));

                Element xEl = doc.createElement("x");
                xEl.appendChild(doc.createTextNode(String.valueOf(thrashLocation.getX())));

                Element yEl = doc.createElement("y");
                yEl.appendChild(doc.createTextNode(String.valueOf(thrashLocation.getY())));

                // Parse employee
                UserShort employee = thrashLocation.getEmployee();
                Element employeeEl = doc.createElement("employee");

                Element roleEl = doc.createElement("role");
                roleEl.appendChild(doc.createTextNode(employee.getRole().toString()));

                Element phoneNrEl = doc.createElement("phone_nr");
                phoneNrEl.appendChild(doc.createTextNode(employee.getPhoneNr()));

                Element idEl = doc.createElement("id");
                idEl.appendChild(doc.createTextNode(employee.getId().toString()));

                Element emailEl = doc.createElement("email");
                emailEl.appendChild(doc.createTextNode(employee.getEmail()));

                // Add Employee Fields
                employeeEl.appendChild(roleEl);
                employeeEl.appendChild(phoneNrEl);
                employeeEl.appendChild(idEl);
                employeeEl.appendChild(emailEl);

                // Add all fields to ThrashLocation
                thrashLocationEl.appendChild(idElement);
                thrashLocationEl.appendChild(addressNameEl);
                thrashLocationEl.appendChild(xEl);
                thrashLocationEl.appendChild(yEl);
                thrashLocationEl.appendChild(employeeEl);

                rootElement.appendChild(thrashLocationEl);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(xmlFile));
            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }
}
