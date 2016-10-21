//Набор функций для обслуживания ЖКИ интерфейс 4-бита 	
#include "lcd.h"            


//-------Общая часть функций lcd_com, lcd_dat----------- ДЛЯ МЛАДШИХ РАЗРЯДОВ ПОРТА
void lcd(unsigned char p) //"p" - байт данных или команд 
{ 
    PORT_lcd &= 0xF0;             //Очистка младших разрядов
    PORT_lcd |= (p >>4);         //Старший ниббл
    _delay_us(10);                //Длительность сигнала EN 
    PORT_lcd &= ~_BV(EN);        //EN=0, фронт записи данных в ЖКИ 
    _delay_us(10);                //Длительность сигнала EN 
    PORT_lcd |= _BV(EN);          //Сигнал EN=1 
    PORT_lcd &= 0xF0;             // Очистка младших разрядов
	PORT_lcd |= (p & 0x0F);       //Младший ниббл 
    _delay_us(10);                //Длительность сигнала EN 
    PORT_lcd &= ~_BV(EN);        //EN=0, фронт записи данных в ЖКИ 
    _delay_us(40);                //Пауза для выполнения команды 
} 

/*/-------Общая часть функций lcd_com, lcd_dat----------- ДЛЯ СТАРШИХ РАЗРЯДОВ ПОРТА
void lcd(unsigned char p) //"p" - байт данных или команд 
{ 
    PORT_lcd &= 0x0F; 
    PORT_lcd |= (p & 0xF0);    //Старший ниббл 
    _delay_us(10);//pause(TIME);                 //Длительность сигнала EN 
    PORT_lcd &= ~_BV(EN);   //EN=0, фронт записи данных в ЖКИ 
    _delay_us(10);//pause(TIME);                 //Длительность сигнала EN 
    PORT_lcd |= _BV(EN);                        //Сигнал EN=1 
    PORT_lcd &= 0x0F; 
	PORT_lcd |= (p << 4);      //Младший ниббл 
    _delay_us(10);//pause(TIME);                 //Длительность сигнала EN 
    PORT_lcd &= ~_BV(EN);   //EN=0, фронт записи данных в ЖКИ 
    _delay_us(40);//pause(5*TIME);          //Пауза для выполнения команды 
}*/                              
                             

//-----------Функция записи команды в ЖКИ--------------- 
void lcd_com(unsigned char p)       //"p" - байт команды 
{
    PORT_lcd &= ~_BV(RS);
    PORT_lcd |= _BV(EN);      //RS=0, EN=1 
    lcd(p);   //Вызов общей части функций lcd_com, lcd_dat   
}                          //Окончание функции "lcd_com" 

//-----------Функция записи данных в ЖКИ---------------- =29
void lcd_dat(unsigned char p)        //"p" - байт данных =30
{
    PORT_lcd |= _BV(RS) | _BV(EN);               //RS=1, EN=1 =31
    lcd(p);   //Вызов общей части функций lcd_com, lcd_dat =32  
}                          //Окончание функции "lcd_dat" =33


//------------Функция инициализации ЖКИ----------------- =34
void lcd_init(void)       //Режим 4 бит, мигающий курсор =35
{
    //DDR_lcd |= 0xF0;//Порт lcd на выход для СТАРШИХ разрядов порта
	DDR_lcd |= 0x0F;//Порт lcd на выход для МЛАДШИХ разрядов порта
	DDR_EN |= _BV(EN);
	DDR_RS |= _BV(RS);
    _delay_ms(10);
	
    lcd_com(0x33);
    _delay_ms(100);          //Подготовка =36
    lcd_com(0x32); 
	lcd_com(0x28);
    lcd_com(0x28);
    lcd_com(0x28);        //4 бит, 2 строки =37
	lcd_com(0x08);             //Полное выключение дисплея =38
	lcd_com(0x01); 
	_delay_ms(200);   //Очистка дисплея =39
    lcd_com(0x06);                  //Сдвиг курсора вправо =40
    lcd_com(0x0C);    //Включение дисплея
	//InitCGram();
}                         

//Функция вывода строки из FLASH на LCD по адресу   
void show_string_flash (unsigned char adress, const unsigned char *string)
    {
	    unsigned char i;
		lcd_com (adress);
        for (i=0; i<16; i++)
	    lcd_dat(pgm_read_byte(&string[i]));  
	}

//Функция вывода строки из ROM на LCD по адресу   
void show_string_rom (unsigned char adress, const unsigned char *string)
    {
	    unsigned char i;
		lcd_com (adress);
        for (i=0; i<16; i++)
	    lcd_dat(string[i]);  
	}

