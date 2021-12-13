from bs4 import BeautifulSoup

collective_intelligence_output = 'title, tracking id, report type (V), report type (ID), TLP class (V), TLP class (ID), ' \
             'record status (V), record tatus (ID), crisis content, special handling (V), special handling (ID), ' \
             'incedent date, document type (V), document type (ID), sources, description\n'

cyber_incident_output = 'title, tracking id, report type (V), report type (ID), TLP class (V), TLP class (ID), ' \
             'record status (V), record status (ID), credibility (V), credibiility (ID), crisis content, special handling (V), special handling (ID), ' \
             'severity (V), severity (ID), incident date, threat indicators, cyber incident type (V), cyber incident type (ID), ' \
             'incident result, port services attacked, sources, summary, description\n'

cyber_threat_output = 'title, tracking id, report type (V), report type (ID), TLP class (V), TLP class (ID), ' \
             'record status (V), record status (ID), credibility (V), credibiility (ID), urgency (V), urgency (ID), risk (V), ' \
                      'risk (ID), crisis content, special handling (V), special handling (ID), severity (V), ' \
                      'severity (ID), incident date, threat indicators, business impact, ' \
                      'cyber threat type (V), cyber threat type (ID), ' \
                      'recommendations, sources, summary, description\n'

cyber_vulnerability_output = 'title, tracking id, report type (V), report type (ID), TLP class (V), TLP class (ID), ' \
                      'record status (V), record status (ID), credibility (V), credibiility (ID), urgency (V), urgency (ID), risk (V), ' \
                      'risk (ID), crisis content, special handling (V), special handling (ID), severity (V), ' \
                      'severity(ID), incident date, vendor products (V), vendor products (ID), advisory number, buisness impact, ' \
                      'change history, corrective action, cve number, sources, summary, description\n'



#STRIP COMMAS EVERYWHERE TO AVOID CURRUPTING CSV FILE
def parse_collective_intelligence (xml_soup):

    # this string will have values added to it, separated by commas (CSV file).
    # It's header will categorizze the values
    output = ''

    output += xml_soup.reportinstance.title.string.replace(',', '') + ', '
    output += xml_soup.reportinstance.trackingid.string + ', '
    output += xml_soup.reportinstance.reporttype.value.string + ', '
    output += xml_soup.reportinstance.reporttype.id.string + ', '
    output += xml_soup.reportinstance.tlpclassification.value.string + ', '
    output += xml_soup.reportinstance.tlpclassification.id.string + ', '

    try: output += xml_soup.reportinstance.recordstatus.value.string + ', '

    except AttributeError: output += 'None, '

    try: output += xml_soup.reportinstance.recordstatus.id.string + ', '

    except AttributeError: output += 'None, '

    output += xml_soup.reportinstance.crisiscontent.string + ', '
    output += xml_soup.reportinstance.specialhandling.value.string + ', '
    output += xml_soup.reportinstance.specialhandling.id.string + ', '
    output += xml_soup.reportinstance.incidentdate.string + ', '

    output += xml_soup.reportinstance.documenttype.value.string + ', '
    output += xml_soup.reportinstance.documenttype.id.string + ', '

    try:
        source = xml_soup.reportinstance.sources.string
        source = strip_tags(source)
        output += source + ', '

    except AttributeError: output += 'None, '

    try:
        desc = xml_soup.reportinstance.description.string
        desc = strip_tags(desc)
        output += desc

    except AttributeError: output += 'None'

    return output + '\n'

def parse_cyber_incident(xml_soup):
    # this string will have values added to it, separated by commas (CSV file).
    # It's header will categorizze the values
    output = ''

    output += xml_soup.reportinstance.title.string.replace(',', '') + ', '
    output += xml_soup.reportinstance.trackingid.string + ', '
    output += xml_soup.reportinstance.reporttype.value.string + ', '
    output += xml_soup.reportinstance.reporttype.id.string + ', '
    output += xml_soup.reportinstance.tlpclassification.value.string + ', '
    output += xml_soup.reportinstance.tlpclassification.id.string + ', '

    try:
        output += xml_soup.reportinstance.recordstatus.value.string + ', '
        output += xml_soup.reportinstance.recordstatus.id.string + ', '
    except:
        output += 'None, '
        output += 'None, '

    output += xml_soup.reportinstance.credibility.value.string + ', '
    output += xml_soup.reportinstance.credibility.id.string + ', '
    output += xml_soup.reportinstance.crisiscontent.string + ', '
    output += xml_soup.reportinstance.specialhandling.value.string + ', '
    output += xml_soup.reportinstance.specialhandling.id.string + ', '
    output += xml_soup.reportinstance.severity.value.string + ', '
    output += xml_soup.reportinstance.severity.id.string + ', '
    output += xml_soup.reportinstance.incidentdate.string + ', '

    try:
        threats = xml_soup.reportinstance.threatindicators.string
        threats = strip_tags(threats)

        output +=  threats + ', '

    except AttributeError:
        output += 'None, '

    output += xml_soup.reportinstance.cyberincidenttype.value.string + ', '
    output += xml_soup.reportinstance.cyberincidenttype.id.string + ', '

    incidentresult = xml_soup.reportinstance.incidentresult.string
    incidentresult = strip_tags(incidentresult)
    output += incidentresult + ', '

    try:
        ports = xml_soup.reportinstance.portsservicesattacked.string
        ports = strip_tags(ports)
        output += ports  + ', '

    except AttributeError:
        output += 'None, '

    try:
        source = xml_soup.reportinstance.sources.string
        source = strip_tags(source)
        output += source + ', '

    except TypeError: output += 'None, '


    summary = xml_soup.reportinstance.summary.string
    summary = strip_tags(summary)
    output += summary + ', '

    desc = xml_soup.reportinstance.description.string
    desc = strip_tags(desc)
    output += desc

    return output + '\n'

def parse_cyber_threat(xml_soup):
    # this string will have values added to it, separated by commas (CSV file).
    # It's header will categorizze the values
    output = ''

    output += xml_soup.reportinstance.title.string.replace(',', '') + ', '
    output += xml_soup.reportinstance.trackingid.string + ', '
    output += xml_soup.reportinstance.reporttype.value.string + ', '
    output += xml_soup.reportinstance.reporttype.id.string + ', '
    output += xml_soup.reportinstance.tlpclassification.value.string + ', '
    output += xml_soup.reportinstance.tlpclassification.id.string + ', '

    try:
        output += xml_soup.reportinstance.recordstatus.value.string + ', '
        output += xml_soup.reportinstance.recordstatus.id.string + ', '
    except:
        output += 'None, '
        output += 'None, '

    output += xml_soup.reportinstance.credibility.value.string + ', '
    output += xml_soup.reportinstance.credibility.id.string + ', '

    try:
        output += xml_soup.reportinstance.urgency.value.string + ', '
    except AttributeError:
        output += 'None, '

    try:
        output += xml_soup.reportinstance.urgency.id.string + ', '
    except AttributeError:
        output += 'None, '

    try:
        output += xml_soup.reportinstance.risk.value.string + ', '
    except AttributeError:
        output += 'None, '

    try:
        output += xml_soup.reportinstance.risk.id.string + ', '
    except AttributeError:
        output += 'None, '

    output += xml_soup.reportinstance.crisiscontent.string + ', '
    output += xml_soup.reportinstance.specialhandling.value.string + ', '
    output += xml_soup.reportinstance.specialhandling.id.string + ', '
    output += xml_soup.reportinstance.severity.value.string + ', '
    output += xml_soup.reportinstance.severity.id.string + ', '

    output += xml_soup.reportinstance.incidentdate.string + ', '

    try:
        threats = xml_soup.reportinstance.threatindicators.string
        threats = strip_tags(threats)
        output +=  threats + ', '

    except AttributeError:
        output += 'None, '


    try:
        impact = xml_soup.reportinstance.businessimpact.text
        impact = strip_tags(impact)
        output += impact + ', '

    except AttributeError:
        output += 'None, '


    output += xml_soup.reportinstance.cyberthreattype.value.string + ', '
    output += xml_soup.reportinstance.cyberthreattype.id.string + ', '


    try:
        recs = xml_soup.reportinstance.recommendations.string
        recs = strip_tags(recs)
        output += recs + ', '

    except AttributeError:
        output += 'None, '

    try:
        source = xml_soup.reportinstance.sources.string
        source = strip_tags(source)
        output += source + ', '

    except AttributeError: output += 'None, '

    summary = xml_soup.reportinstance.summary.string
    summary = strip_tags(summary)
    output += summary + ', '

    desc = xml_soup.reportinstance.description.string
    desc = strip_tags(desc)
    output += desc

    return output + '\n'

def parse_cyber_vulnerability(xml_soup):
    output = ''
    output += xml_soup.reportinstance.title.string.replace(',', '') + ', '
    output += xml_soup.reportinstance.trackingid.string + ', '
    output += xml_soup.reportinstance.reporttype.value.string + ', '
    output += xml_soup.reportinstance.reporttype.id.string + ', '
    output += xml_soup.reportinstance.tlpclassification.value.string + ', '
    output += xml_soup.reportinstance.tlpclassification.id.string + ', '

    try:
        output += xml_soup.reportinstance.recordstatus.value.string + ', '
    except AttributeError:
        output += 'None, '

    try:
        output += xml_soup.reportinstance.recordstatus.id.string + ', '
    except AttributeError:
        output += 'None, '

    output += xml_soup.reportinstance.credibility.value.string + ', '
    output += xml_soup.reportinstance.credibility.id.string + ', '

    try:
        output += xml_soup.reportinstance.urgency.value.string + ', '
    except AttributeError:
        output += 'None, '

    try:
        output += xml_soup.reportinstance.urgency.id.string + ', '
    except AttributeError:
        output += 'None, '

    try:
        output += xml_soup.reportinstance.risk.value.string + ', '
    except AttributeError:
        output += 'None, '

    try:
        output += xml_soup.reportinstance.risk.id.string + ', '
    except AttributeError:
        output += 'None, '

    output += xml_soup.reportinstance.crisiscontent.string + ', '

    output += xml_soup.reportinstance.specialhandling.value.string + ', '
    output += xml_soup.reportinstance.specialhandling.id.string + ', '
    output += xml_soup.reportinstance.severity.value.string + ', '
    output += xml_soup.reportinstance.severity.id.string + ', '
    output += xml_soup.reportinstance.incidentdate.string + ', '

    try:
        s = soup.reportinstance.vendorproducts.vendor.text
        result = ''.join(i for i in s if not i.isdigit())

        if result == 'F':
            result += '5'

        output += result + ', '

    except AttributeError:
        output += 'None, '

    try:
        output += soup.reportinstance.vendorproducts.id.string + ', '
    except AttributeError:
        output += 'None, '

    try:
        output += xml_soup.reportinstance.advisoryidnumber.string + ', '
    except TypeError:
        output += 'None, '

    try:
        output += xml_soup.reportinstance.businessimpact.text + ', '
    except AttributeError:
        output += 'None, '

    try:
        output += xml_soup.reportinstance.changehistory.string.replace(',', '') + ', '
    except AttributeError:
        output += 'None, '

    correctiveaction = xml_soup.reportinstance.correctiveaction.string
    correctiveaction = strip_tags(correctiveaction)
    output += correctiveaction + ', '

    # parse cve number
    cvenum = xml_soup.reportinstance.cvenumber.string
    cvenum = strip_tags(cvenum)

    output += cvenum + ', '

    try:
        source = xml_soup.reportinstance.sources.string
        source = strip_tags(source)
        output += source + ', '

    except TypeError:
        output += 'None, '

    summary = xml_soup.reportinstance.summary.string
    summary = strip_tags(summary)
    output += summary + ', '

    desc = xml_soup.reportinstance.description.string
    desc = strip_tags(desc)
    output += desc

    return output + '\n'

def strip_tags(text):
    text = text.replace(',', '')
    text = text.replace('<b>', '')
    text = text.replace('</b>', '')
    text = text.replace('<br />', ' ')
    text = text.replace('<br>', ' ')
    text = text.replace('<p>', '')
    text = text.replace('</p>', ' ')
    text = text.replace('<div>', '')
    text = text.replace('</p>', '')
    text = text.replace('&nbsp;', '')
    text = text.replace('</div>', '')
    text = text.replace('<strong>', '')
    text = text.replace('</strong>', '')

    if len(text) > 32760 :
        text = 'error input char limit exceeeded'

    return text


##################################################################################################################
# pulls xml code from email file by calling methods above, then creates a csv with the strings returned by methods
with open('FS-ISAC emails to date.txt') as f:

    for line in f:
        soup = BeautifulSoup(line, 'html.parser')

        if line.startswith('<?xml'):

            if soup.reportinstance.reporttype.value.string == 'Collective Intelligence':
                collective_intelligence_output += parse_collective_intelligence(soup)

            if soup.reportinstance.reporttype.value.string == 'Cyber Incident':
                cyber_incident_output += parse_cyber_incident(soup)

            if soup.reportinstance.reporttype.value.string == 'Cyber Threat':
                cyber_threat_output += parse_cyber_threat(soup)

            if soup.reportinstance.reporttype.value.string == 'Cyber Vulnerability':
                cyber_vulnerability_output += parse_cyber_vulnerability(soup)

    #CREATE CSV FOR EACH REPORT TYPE OUTSIDE OF LOOP

    w = open ('Collective Intelligence.csv', 'w')
    # writes str CSV file named with the text above
    w.write(collective_intelligence_output)
    w.close()

    w = open ('Cyber Incident.csv', 'w')
    # writes str CSV file named with the text above
    w.write(cyber_incident_output)
    w.close()

    w = open ('Cyber Threat.csv', 'w')
    # writes str CSV file named with the text above
    w.write(cyber_threat_output)
    w.close()

    w = open ('Cyber Vulnerability.csv', 'w')
    # writes str CSV file named with the text above
    w.write(cyber_vulnerability_output)
    w.close()
