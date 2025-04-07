# SE-Lab---HW2

# در مرحله اول، مراحل اصلی پروژه را(که شامل ۴ بخش کلی به همراه ریزجزئیات می‌باشند) شرح می‌دهیم و بورد کانبان را به منظور پیشبرد اهداف پروه برای اعضای تیم، طراحی می‌کنیم:

    مرحله ۱: تحلیل و شناسایی Code Smell
    مرحله ۲: بازآرایی برای کپسوله‌سازی و انتزاع
    مرحله ۳: معرفی وراثت و چندریختی از طریق رابط PaymentGateway
    مرحله ۴: بازآرایی پیشرفته و یکپارچه‌سازی (dependency injection و مدیریت پیکربندی)

# گزارش مرحله اول

در مرحله اول، تسک اول، مطالعه کد یکپارچه ارائه شده(که به زبان جاوا نوشته شده است) بوده و بنابر اصول SOLID، باید نواقص و یا مشکلات کد را مشخص کرد. ۴ مورد در صورت آزمایش برای بررسی لحاظ شده اند که هر یک را به طور جداگانه در مراحل مجزا، بررسی می‌کنیم:

# مرحله اول: بررسی وابستگی بیش از حد!

در کد ارائه شده، متد processPayment وابسته به متدهای processCreditCard, processDigitalWallet, و processBankTransfer بوده که این موضوع، می‌تواند بعدا برای ارتقا و بهبود روش های پرداخت و اضافه کردن متد های دیگر، مشکل ساز شود. این موضوع با اصول SOLID در تناقض است.

در این بخش، اصل SRP نقض شده است، چرا که تم مسئولیت بودن متد ها را نداریم. همچنین اصل جداسازی رابط ها یا همان ISP نیز نقض شده است، چرا که رابط کاربری ما پیچیده تر از حالت استاندارد و نرمال خود است.

# مرحله دوم: متدهای طولانی!

می‌توان در این مورد، به متد validatePayment اشاره کرد، چرا که دارای if و case های زیادی بوده و ممکن است در زمان بهبود کد، ارتقا آن و یا اضافه کردن هدف دیگری به سیستم، مشکل ساز شود.

در این بخش، اصل SRP نقض شده است، چرا که تم مسئولیت بودن متد ها را نداریم. همچنین اصل جداسازی رابط ها یا همان ISP نیز نقض شده است، چرا که رابط کاربری ما پیچیده تر از حالت استاندارد و نرمال خود است.

# مرحله سوم: تکرار کد!

واضح است که در کد ارائه شده، متد هایی مانند processCreditCard, processDigitalWallet, processBankTransfer، کار مشترکی را انجام می‌دهند و تکرار کد زیادی در این بخش، شاهد هستیم. می‌توان برای درست کردن این بخش، از یک متد و یا کلاس مشترک استفاده کرد و تغییرات جزئی در خروجی آن در بخش های مختلف، لحاظ کرد.

در این بخش، اصل تک‌مسئولیتی یا همان SRP از اصول SOLID نقض شده است، همچنین می‌توان بیان کرد که اصل باز-بسته یا همان OCP نیز نقض شده است، چرا که در صورت تصمیم برای ارتقا کد، کد برای گسترش آماده نیست!

# مرحله چهارم: مسئولیت‌های نامشخص!

در کد ارائه شده، کلاس PaymentProcessor، مسئولیت های زیر را شامل می‌شود که باعث پیچیدگی این بخش از کد می‌تواند باشد و بهتر است که هر کدام از مسئولیت های بیان شده، در یک کلاس یا متد جداگانه نوشته شوند تا بعدا، در صورت نیاز به اصلاح و یا بهبود، بتوان ساده تر کار را پیش برد:

[پردازش پرداخت(PaymentProcessor)](#)

[اعتبارسنجی(validatePayment)](#)

[گرفتن لاگ(logTransaction)](#)

خود است که هر کدام از این بخش ها، در کلاس جداگانه نوشته بشوند.

اصول نقض شده در مرحله چهارم نیز، مشابه مرحله سوم(کد تکراری) هستند.

# نتیجه‌گیری مرحله اول

گزارش مرحله اول پروژه، در این بخش به پایان می‌رسد. توضیحات مربوط به کد، نواقص موجود با توجه به اصول SOLID و اینکه در هر بخش، کدام یک از اصول نقض شده اند، به صورت کامل توضیح داده شده اند.
---

# سیستم پرداخت - مرحله ۴: یکپارچه‌سازی و بازآرایی پیشرفته

این مستند تغییرات و بهبودهای اعمال‌شده در **مرحله ۴** پروژه سیستم پرداخت را توضیح می‌دهد. در این مرحله تمرکز اصلی بر جداسازی وابستگی‌های خارجی (مانند URLهای API و تنظیمات دیگر) از منطق اصلی برنامه، استفاده از تکنیک‌های Dependency Injection و بهبود نگهداری سیستم است.

---

## اهداف مرحله ۴

- **مدیریت پیکربندی خارجی:**  
  نگهداری اطلاعات حساس مانند URLهای API در فایل پیکربندی (مثلاً `config.properties`) به جای hard-code شدن در کد.
- **Dependency Injection:**  
  تزریق وابستگی‌ها (مانند PaymentGatewayها) به صورت داینامیک از طریق سازنده‌ها به جای ایجاد مستقیم اشیاء در داخل کلاس‌ها.

- **ایزوله‌سازی کد:**  
  جداسازی منطق‌های پردازش پرداخت، یکپارچه‌سازی درگاه‌ها و مدیریت پیکربندی به ماژول‌های مجزا به منظور رعایت اصول SOLID و بهبود قابلیت نگهداری.

---

## تغییرات اعمال‌شده

1. **فایل پیکربندی (`config.properties`):**

   - اطلاعات مربوط به URLهای API مانند `credit_card_endpoint`، `digital_wallet_endpoint` و `bank_transfer_endpoint` در این فایل ذخیره شده‌اند.

2. **کلاس ConfigurationManager:**

   - این کلاس مسئول خواندن فایل پیکربندی و ارائه مقادیر تنظیمات به سایر بخش‌های سیستم است.

3. **به‌روزرسانی کلاس BaseGateway و درگاه‌های پرداخت:**

   - کلاس **BaseGateway** به‌روزرسانی شده تا به جای دریافت یک Map پیکربندی، از یک شیء **ConfigurationManager** استفاده کند.
   - متد `log` در **BaseGateway** به گونه‌ای تغییر یافته است که تاریخ و زمان را در گزارش‌ها نمایش دهد.
   - کلاس‌های **CreditCardGateway**، **DigitalWalletGateway** و **BankTransferGateway** به‌روزرسانی شده‌اند تا وابستگی پیکربندی از طریق سازنده دریافت و استفاده شود.

4. **Dependency Injection در PaymentProcessor:**
   - کلاس **PaymentProcessor** همچنان از یک شیء از نوع **PaymentGateway** استفاده می‌کند، اما اکنون این شیء به صورت داینامیک از خارج تزریق می‌شود.
   - این امر امکان تغییر داینامیک درگاه پرداخت (مثلاً تغییر از کارت اعتباری به کیف پول دیجیتال) بدون تغییر در منطق اصلی پردازش پرداخت را فراهم می‌کند.

---

## ساختار فایل‌ها در این مرحله

- **config.properties:**  
  فایل پیکربندی شامل URLهای API و سایر تنظیمات محیطی.

- **ConfigurationManager.java:**  
  کلاس مدیریت پیکربندی که فایل `config.properties` را بارگذاری و مقادیر آن را ارائه می‌دهد.

- **BaseGateway.java:**  
  کلاس انتزاعی به‌روزرسانی‌شده جهت اشتراک‌گذاری منطق مشترک (از جمله ثبت گزارش با تاریخ و زمان) برای درگاه‌های پرداخت.

- **CreditCardGateway.java:**  
  پیاده‌سازی به‌روزرسانی‌شده درگاه پرداخت کارت اعتباری با استفاده از **ConfigurationManager**.

- **DigitalWalletGateway.java:**  
  پیاده‌سازی به‌روزرسانی‌شده درگاه پرداخت کیف پول دیجیتال با استفاده از **ConfigurationManager**.

- **BankTransferGateway.java:**  
  پیاده‌سازی به‌روزرسانی‌شده درگاه انتقال بانکی با استفاده از **ConfigurationManager**.

- **PaymentProcessor.java:**  
  کلاس پردازش پرداخت که وابستگی‌های مربوط به درگاه پرداخت را از طریق سازنده دریافت می‌کند.

---

## نحوه اجرای سیستم

1. **کامپایل فایل‌ها:**  
   تمامی فایل‌های بالا (از جمله فایل پیکربندی) بایستی در یک پوشه قرار داشته باشند و به درستی کامپایل شوند.

2. **اجرای برنامه:**  
   با اجرای کلاس **Main**، ابتدا پیکربندی از فایل `config.properties` بارگذاری شده و شیء مناسب از درگاه پرداخت با استفاده از **ConfigurationManager** ایجاد می‌شود. سپس کلاس **PaymentProcessor** با تزریق این درگاه ساخته شده و پرداخت انجام می‌شود. خروجی نهایی در کنسول نمایش داده می‌شود.

---

## نتیجه نهایی

- وابستگی‌های خارجی از منطق اصلی برنامه جدا شده و نگهداری تنظیمات به صورت جداگانه امکان‌پذیر شده است.
- استفاده از Dependency Injection امکان تغییر داینامیک درگاه‌های پرداخت را فراهم کرده و نگهداری و گسترش سیستم را ساده‌تر می‌کند.
- اصول SOLID، به‌ویژه اصل تک‌مسئولیتی رعایت شده و سیستم به صورت مدولار و مقیاس‌پذیر طراحی شده است.
