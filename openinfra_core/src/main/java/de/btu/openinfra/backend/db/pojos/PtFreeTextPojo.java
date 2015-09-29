package de.btu.openinfra.backend.db.pojos;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PtFreeTextPojo extends OpenInfraPojo {

    protected List<LocalizedString> localizedStrings;

    public PtFreeTextPojo() {
    }

    public PtFreeTextPojo(List<LocalizedString> ls, UUID id) {
        this.setUuid(id);
        this.localizedStrings = ls;
    }

    public PtFreeTextPojo(List<LocalizedString> ls, UUID id, int trid) {
        this.setUuid(id);
        this.setTrid(trid);
        this.localizedStrings = ls;
    }

    public List<LocalizedString> getLocalizedStrings() {
        return localizedStrings;
    }

    public void setLocalizedStrings(List<LocalizedString> localizedStrings) {
        this.localizedStrings = localizedStrings;
    }

    @Override
    protected void makePrimerHelper() {
        localizedStrings = new ArrayList<LocalizedString>();
        localizedStrings.add(new LocalizedString());
        localizedStrings.get(0).setCharacterString("");
        localizedStrings.get(0).setLocale(new PtLocalePojo());
        localizedStrings.get(0).getLocale().makePrimerHelper();
    }

}
