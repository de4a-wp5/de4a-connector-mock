package eu.de4a.connector.mock.controller;

import com.helger.commons.error.level.EErrorLevel;
import eu.de4a.iem.jaxb.common.types.RequestTransferEvidenceUSIDTType;
import eu.de4a.iem.xml.de4a.DE4AMarshaller;
import eu.de4a.iem.xml.de4a.DE4AResponseDocumentHelper;
import eu.de4a.iem.xml.de4a.EDE4ACanonicalEvidenceType;
import eu.de4a.iem.xml.de4a.IDE4ACanonicalEvidenceType;
import eu.de4a.kafkaclient.DE4AKafkaClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
@Profile("dt")
public class DTController {

    @PostMapping("${mock.dt.endpoint.usi}")
    public ResponseEntity<String> dt1usiresp(InputStream body) throws MarshallException {
        //todo: check dataowner and use CanonicalEvidenceType from Pilot enum.
        var marshaller = DE4AMarshaller.dtUsiRequestMarshaller(
                IDE4ACanonicalEvidenceType.multiple(
                        EDE4ACanonicalEvidenceType.T42_COMPANY_INFO_V06,
                        EDE4ACanonicalEvidenceType.T41_UC1_2021_04_13));
        UUID errorKey = UUID.randomUUID();
        marshaller.readExceptionCallbacks().set((ex) -> {
            MarshallErrorHandler.getInstance().postError(errorKey, ex);
        });
        RequestTransferEvidenceUSIDTType req = marshaller.read(body);
        if (req == null) {
            throw new MarshallException(errorKey);
        }
        DE4AKafkaClient.send(EErrorLevel.INFO,
                String.format("Receiving USI RequestTransferEvidence, requestId: %s", req.getRequestId() ));

        var res = DE4AResponseDocumentHelper.createResponseError(true);
        return ResponseEntity.status(HttpStatus.OK).body(DE4AMarshaller.dtUsiResponseMarshaller().getAsString(res));
    }

}
