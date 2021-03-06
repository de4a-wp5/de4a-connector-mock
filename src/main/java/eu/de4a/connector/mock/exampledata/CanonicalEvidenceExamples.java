package eu.de4a.connector.mock.exampledata;

import com.helger.jaxb.GenericJAXBMarshaller;
import eu.de4a.iem.xml.de4a.t41.v2021_04_13.DE4AT41Marshaller;
import eu.de4a.iem.xml.de4a.t42.v0_6.DE4AT42Marshaller;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.w3c.dom.Element;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Slf4j
public enum CanonicalEvidenceExamples {

    T42_SE("5591674170", new ClassPathResource("examples/T4.2-examples/sample company info SE -2.xml"),  DataOwner.V_SE, EvidenceID.COMPANY_REGISTRATION, DE4AT42Marshaller.legalEntity(), USIAutoResponse.OFF),
    T42_NL("90000471", new ClassPathResource("examples/T4.2-examples/sample CompanyInfo NL KVK.xml"),  DataOwner.COC_NL, EvidenceID.COMPANY_REGISTRATION, DE4AT42Marshaller.legalEntity(), USIAutoResponse.OFF),
    T42_RO("J40/12487/1998", new ClassPathResource("examples/T4.2-examples/sample CompanyInfo RO ONRC-2.xml"),  DataOwner.ONRC_RO, EvidenceID.COMPANY_REGISTRATION, DE4AT42Marshaller.legalEntity(),  USIAutoResponse.OFF),
    T42_AT("???", new ClassPathResource("examples/T4.2-examples/sample CompanyInfo AT.xml"),  DataOwner.DMDW_AT, EvidenceID.COMPANY_REGISTRATION, DE4AT42Marshaller.legalEntity(), USIAutoResponse.OFF),
    T41_PT("123456789", new ClassPathResource("examples/T4.1-examples/SA-UC1-11-02-2021-example-PT.xml"), DataOwner.UL_PT, EvidenceID.HIGHER_EDUCATION_DIPLOMA, DE4AT41Marshaller.higherEducationDiploma(), USIAutoResponse.OFF),
    T41_SI("123456", new ClassPathResource("examples/T4.1-examples/SA-UC1-example-SI.xml"), DataOwner.MIZS_SI, EvidenceID.HIGHER_EDUCATION_DIPLOMA, DE4AT41Marshaller.higherEducationDiploma(), USIAutoResponse.OFF),
    T41_ES("53377873W", new ClassPathResource("examples/T4.1-examples/SA-UC1-example-ES.xml"), DataOwner.SGAD_ES, EvidenceID.HIGHER_EDUCATION_DIPLOMA, DE4AT41Marshaller.higherEducationDiploma(), USIAutoResponse.OFF),
    T41_PT_A("123456789A", new ClassPathResource("examples/T4.1-examples/SA-UC1-11-02-2021-example-PT_A.xml"), DataOwner.UL_PT, EvidenceID.HIGHER_EDUCATION_DIPLOMA, DE4AT41Marshaller.higherEducationDiploma(), USIAutoResponse.IMMEDIATE),
    T41_SI_A("123456A", new ClassPathResource("examples/T4.1-examples/SA-UC1-example-SI_A.xml"), DataOwner.MIZS_SI, EvidenceID.HIGHER_EDUCATION_DIPLOMA, DE4AT41Marshaller.higherEducationDiploma(), USIAutoResponse.IMMEDIATE),
    T41_ES_A("53377873WA", new ClassPathResource("examples/T4.1-examples/SA-UC1-example-ES_A.xml"), DataOwner.SGAD_ES, EvidenceID.HIGHER_EDUCATION_DIPLOMA, DE4AT41Marshaller.higherEducationDiploma(), USIAutoResponse.IMMEDIATE),
    T41_PT_B("123456789B", new ClassPathResource("examples/T4.1-examples/SA-UC1-11-02-2021-example-PT_B.xml"), DataOwner.UL_PT, EvidenceID.HIGHER_EDUCATION_DIPLOMA, DE4AT41Marshaller.higherEducationDiploma(), USIAutoResponse.DELAY_5_SEC),
    T41_SI_B("123456B", new ClassPathResource("examples/T4.1-examples/SA-UC1-example-SI_B.xml"), DataOwner.MIZS_SI, EvidenceID.HIGHER_EDUCATION_DIPLOMA, DE4AT41Marshaller.higherEducationDiploma(), USIAutoResponse.DELAY_5_SEC),
    T41_ES_B("53377873WB", new ClassPathResource("examples/T4.1-examples/SA-UC1-example-ES_B.xml"), DataOwner.SGAD_ES, EvidenceID.HIGHER_EDUCATION_DIPLOMA, DE4AT41Marshaller.higherEducationDiploma(), USIAutoResponse.DELAY_5_SEC),
    T41_PT_C("123456789C", new ClassPathResource("examples/T4.1-examples/SA-UC1-11-02-2021-example-PT_C.xml"), DataOwner.UL_PT, EvidenceID.HIGHER_EDUCATION_DIPLOMA, DE4AT41Marshaller.higherEducationDiploma(), USIAutoResponse.DELAY_40_SEC),
    T41_SI_C("123456C", new ClassPathResource("examples/T4.1-examples/SA-UC1-example-SI_C.xml"), DataOwner.MIZS_SI, EvidenceID.HIGHER_EDUCATION_DIPLOMA, DE4AT41Marshaller.higherEducationDiploma(), USIAutoResponse.DELAY_40_SEC),
    T41_ES_C("53377873WC", new ClassPathResource("examples/T4.1-examples/SA-UC1-example-ES_C.xml"), DataOwner.SGAD_ES, EvidenceID.HIGHER_EDUCATION_DIPLOMA, DE4AT41Marshaller.higherEducationDiploma(), USIAutoResponse.DELAY_40_SEC),
    T41_PT_D("123456789D", new ClassPathResource("examples/T4.1-examples/SA-UC1-11-02-2021-example-PT_D.xml"), DataOwner.UL_PT, EvidenceID.HIGHER_EDUCATION_DIPLOMA, DE4AT41Marshaller.higherEducationDiploma(), USIAutoResponse.DELAY_240_SEC),
    T41_SI_D("123456D", new ClassPathResource("examples/T4.1-examples/SA-UC1-example-SI_D.xml"), DataOwner.MIZS_SI, EvidenceID.HIGHER_EDUCATION_DIPLOMA, DE4AT41Marshaller.higherEducationDiploma(), USIAutoResponse.DELAY_240_SEC),
    T41_ES_D("53377873WD", new ClassPathResource("examples/T4.1-examples/SA-UC1-example-ES_D.xml"), DataOwner.UJI_ES, EvidenceID.HIGHER_EDUCATION_DIPLOMA, DE4AT41Marshaller.higherEducationDiploma(), USIAutoResponse.DELAY_240_SEC);

    @Getter
    final private String identifier;
    @Getter
    final private Resource resource;
    @Getter
    final private DataOwner dataOwner;
    @Getter
    final private EvidenceID evidenceID;
    @Getter
    final private GenericJAXBMarshaller marshaller;
    @Getter
    final private USIAutoResponse usiAutoResponse;
    private Element documentElement;
    private final Pattern eIDASIdentifierPattern;

    private CanonicalEvidenceExamples(String identifier,
                                      Resource resource,
                                      DataOwner dataOwner,
                                      EvidenceID evidenceID,
                                      GenericJAXBMarshaller marshaller,
                                      USIAutoResponse usiAutoResponse
    ) {
        this.identifier = identifier;
        this.resource = resource;
        this.dataOwner = dataOwner;
        this.evidenceID = evidenceID;
        this.marshaller = marshaller;
        this.usiAutoResponse = usiAutoResponse;
        this.eIDASIdentifierPattern = Pattern.compile(String.format("^%s/[A-Z]{2}/%s$", Pattern.quote(dataOwner.getCountry().toUpperCase(Locale.ROOT)), Pattern.quote(identifier.toUpperCase(Locale.ROOT))));
    }

    public Element getDocumentElement() {
        if (documentElement == null) {
            try { // this is tested for all CanonicalEvidences in CanonicalEvidenceExamplesTest. It should not ever fail.
                documentElement = marshaller.getAsDocument(marshaller.read(resource.getInputStream())).getDocumentElement();
            } catch (IOException ex) {
                log.error("resource {} can't be marshalled, {}", resource.getFilename(), ex.getMessage());
                ex.printStackTrace();
                return null;
            }
        }
        return documentElement;
    }

    public boolean isEIDASIdentifier(String eIDASIdentifier) {
        log.debug("Pattern: {}", eIDASIdentifierPattern.pattern());
        return eIDASIdentifierPattern.matcher(eIDASIdentifier).find();
    }

    private static Stream<CanonicalEvidenceExamples> getCanonicalEvidenceStream(DataOwner dataOwner, EvidenceID evidenceID, String eIDASIdentifier) {
        return Arrays.stream(CanonicalEvidenceExamples.values())
                .filter(canonicalEvidenceExamples -> canonicalEvidenceExamples.dataOwner.equals(dataOwner))
                .filter(canonicalEvidenceExamples -> canonicalEvidenceExamples.evidenceID.equals(evidenceID))
                .filter(canonicalEvidenceExamples -> canonicalEvidenceExamples.isEIDASIdentifier(eIDASIdentifier.toUpperCase(Locale.ROOT)));
    }

    public static CanonicalEvidenceExamples getCanonicalEvidence(DataOwner dataOwner, EvidenceID evidenceID, String eIDASIdentifier) {
        return CanonicalEvidenceExamples.getCanonicalEvidenceStream(dataOwner, evidenceID, eIDASIdentifier)
                .findFirst()
                .orElseGet(() -> null);
    }

    public static Element getDocumentElement(DataOwner dataOwner, EvidenceID evidenceID, String eIDASIdentifier) {
        return CanonicalEvidenceExamples.getCanonicalEvidenceStream(dataOwner, evidenceID, eIDASIdentifier)
                .findFirst()
                .map(CanonicalEvidenceExamples::getDocumentElement)
                .orElseGet(() -> null);
    }
}
