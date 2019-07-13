/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package detector;

import com.google.common.base.Optional;
import com.optimaize.langdetect.LanguageDetector;
import com.optimaize.langdetect.LanguageDetectorBuilder;
import com.optimaize.langdetect.i18n.LdLocale;
import com.optimaize.langdetect.ngram.NgramExtractors;
import com.optimaize.langdetect.profiles.LanguageProfile;
import com.optimaize.langdetect.profiles.LanguageProfileReader;
import com.optimaize.langdetect.text.CommonTextObjectFactories;
import com.optimaize.langdetect.text.TextObject;
import com.optimaize.langdetect.text.TextObjectFactory;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author DucPC
 */
public class Detector {
    public String detect_language(String text) throws IOException{
        List<LanguageProfile> languageProfiles = new LanguageProfileReader().readAllBuiltIn();
        
        LanguageDetector languageDetector = LanguageDetectorBuilder.create(NgramExtractors.standard())
        .withProfiles(languageProfiles)
        .build();
        
        TextObjectFactory textObjectFactory = CommonTextObjectFactories.forDetectingOnLargeText();
        
        TextObject textObject = textObjectFactory.forText(text);
        
        Optional<LdLocale> lang = languageDetector.detect(textObject);
        
        return lang.get().toString();
    }
    
    public static void main(String[] args) throws IOException {
        Detector detector = new Detector();
        
        String text = "\"Muc tieu cua khuon kho do an la xay dung duoc mot Engine Text-To-Speech chay tren nen tang he dieu hanh Android voi ngon ngu la Tieng Viet. Trong qua trinh nghien cuu va lam viec tai Vien Nghien cuu Quoc Te MICA da thu duoc ket qua la Engine Text-To-Speech su dung bo tong hop Tieng Viet do MICA nghien cuu va phat trien. Qua qua trinh lam viec va nghien cuu tai Vien MICA va su huong dan cua thay ThS. Le Tan Hung, TS. Tran Đo Dat cung TS. Mac Đang Khoa, ket qua thu duoc kha kha quan. Va bao cao do an nay se trinh bay cac van de da tim hieu va thuc hien duoc trong suot qua trinh do an.\\n\" +\n";
        
        String result = detector.detect_language(text);
        
        System.out.println(result);
    }
}
