package kg.sunrise.hightime.Auth.Register.Dogovor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import kg.sunrise.hightime.R;

public class DogovorActivity extends AppCompatActivity {
    TextView docx1, docx2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme2);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogovor);
        docx1 = findViewById(R.id.docx1);
        docx2 = findViewById(R.id.docx2);
        docx1.setText("ДОГОВОР-ОФЕРТА");
        docx2.setText("1. Общие положения \n" +
                "1.1. Настоящий Договор является официальным предложениемОсООHightime (в дальнейшем «Исполнитель») для полностью дееспособного физического (далее – «Студент»), которое примет настоящее предложение, на указанных ниже условиях. \n" +
                "1.2. В соответствии с  Гражданским Кодексом КР, в случае принятия изложенных ниже условий и оплаты услуг юридическое или физическое лицо, производящее акцепт этой оферты, становится Студентом  \n" +
                "1.3. Моментом полного и безоговорочного принятия Студентом предложения Исполнителя заключить Договор оферты  считается факт подтверждения готовности совершить оплату услуги Исполнителя, при платеже на сайте http://htlife.biz/ru. \n" +
                "Текст настоящего Договора-оферты (далее по тексту – «Договор») расположен по адресу: http://htlife.biz/ru\n" +
                "1.4. Осуществляя акцепт Договора в порядке, определенном п. 1.3 Договора, Студент подтверждает, что он ознакомлен, согласен, полностью и безоговорочно принимает все условия \n" +
                "Договора в том виде, в каком они изложены в тексте Договора, в том числе в приложениях к Договору, являющихся его неотъемлемой частью. \n" +
                "1.5. Клиент согласен, что акцепт Договора в порядке, указанном в п. 1.2 Договора является заключением Договора на условиях, изложенных в нем. \n" +
                "1.6. Договор не может быть отозван. \n" +
                "1.7. Договор не требует скрепления печатями и/или подписания Студентом и Исполнителем (далее по тексту - Стороны) и сохраняет при этом юридическую силу. \n" +
                "\n" +
                "2. Предмет договора  \n" +
                "2.1. Предметом настоящего Договора является возмездное оказание Исполнителем образовательных  услуг  в соответствии с условиями настоящего Договора. \n" +
                "2.2. Студент полностью принимает условия Договора и оплачивает услуги Исполнителя в соответствии с условиями настоящего Договора. \n" +
                "\n" +
                "3. Оплата Услуг  \n" +
                "3.1. Стоимость услуг по Договору определяется в соответствии с действующими ценами и прописана на сайтеhttp://htlife.biz/ru\n" +
                "3.2. Стоимость услуги может быть изменена Исполнителем в одностороннем порядке.  \n" +
                "3.3. Способы оплаты услуги указаны при оформлении платежа. \n" +
                "\n" +
                "4. Интеллектуальная собственность  \n" +
                "4.1. Вся текстовая информация и графические изображения, находящиеся на сайте http://htlife.biz/ruявляются собственностью Исполнителя. \n" +
                "\n" +
                "5.\tОсобые условия и ответственность сторон.  \n" +
                "5.1.\tИсполнитель несет ответственность за своевременность предоставляемых услуг при выполнении Студентом  установленных требований и правил, размещенных на сайте http://htlife.biz/ru\n" +
                "5.2.\tИсполнитель освобождается от ответственности за нарушение условий Договора, если такое нарушение вызвано действием обстоятельств непреодолимой силы (форс-мажор), включая: \n" +
                "действия органов государственной власти, пожар, наводнение, землетрясение, другие стихийные действия, отсутствие электроэнергии, забастовки, гражданские волнения, беспорядки, любые иные обстоятельства, не ограничиваясь перечисленным, которые могут повлиять на выполнение Исполнителем Договора. \n" +
                "5.3.\tИсполнитель не несет ответственности за качество каналов связи общего пользования или служб, предоставляющих доступ Студента к его услугам. \n" +
                "\n" +
                "6. Конфиденциальность и защита персональной информации  \n" +
                "6.1. Исполнитель обязуется не разглашать полученную от Студента информацию. \n" +
                "6.2. Не считается нарушением обязательств разглашение информации в соответствии с обоснованными и применимыми требованиями закона. \n" +
                "6.3. Исполнитель получает информацию об IP-адресе посетителя Сайта \n" +
                "http://htlife.biz/ru . Данная информация не используется для установления личности посетителя. \n" +
                "6.4. Исполнитель не несет ответственности за сведения, предоставленные Студентом на сайте http://htlife.biz/ruв общедоступной форме. \n" +
                "\n" +
                "7. Порядок рассмотрения претензий и споров  \n" +
                "7.1. Претензии Студента по предоставляемым услугам принимаются Исполнителем к рассмотрению по электронной почте в течение 2 (рабочих) дней с момента возникновения спорной ситуации. \n" +
                "7.2. При рассмотрении спорных ситуаций Исполнитель вправе запросить у Студента  всю интересующую документацию относительно рассматриваемого мероприятия. В случае непредоставленияСтудентом  документов в течение 1 рабочего дня после дня требования, претензия рассмотрению Исполнителем не подлежит. \n" +
                "7.3. Исполнитель и Студент, принимая во внимания характер оказываемой услуги, обязуются в случае возникновения споров и разногласий, связанных с оказанием услуг, применять досудебный порядок урегулирования спора. В случае невозможности урегулирования спора в досудебном порядке стороны вправе обратиться в суд. \n" +
                "\n" +
                "8. Прочие условия  \n" +
                "8.1. Студент обладает всеми правами и полномочиями, необходимыми для заключения и исполнения Договора. \n" +
                "8.2. Студент  вправе в любое время в одностороннем порядке отказаться от услуг Исполнителя. В случае одностороннего отказа Студента  от услуг Исполнителя произведенная оплата не возвращается, и не  может быть  перенесена на другую услугу. \n" +
                "8.3. Исполнитель оставляет за собой право изменять или дополнять любые из условий настоящего Договора в любое время, опубликовывая все изменения на своем сайте. \n" +
                "8.4. По всем вопросам, не урегулированным настоящим Договором, стороны руководствуются действующим законодательством КР. \n" +
                "8.5. Признание судом недействительности какого-либо положения настоящего Договора и правил не влечет за собой недействительность остальных положений. \n" +
                "\n" +
                "9. Реквизиты исполнителя \n" +
                "ОсОО «HighTime» (Хай Тайм) \n" +
                "Юрид. адрес:Кыргызская Республика, г.Бишкек ул.Тыныстанова 8/1 \n" +
                "\n" +
                "10. Контактные данные \n" +
                "+996 505 444 415 \n" +
                "+996 755 444 459\n" +
                "e-mail: htlife.2020@gmail.com\n");


    }
}
