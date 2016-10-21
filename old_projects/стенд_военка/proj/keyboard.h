//******************************************************************************
//
//  Author(s)...: Pashgan    http://ChipEnable.Ru   
//
//  Target(s)...: любой
//
//  Compiler....: любой
//
//  Description.: Универсальный драйвер матричной клавиатуры
//
//  Data........: 12.10.2011
//
//******************************************************************************
#ifndef KEYBOARD_H
#define KEYBOARD_H

//раздел для настройки конфигурации*********************************************

//тактовая частота микроконтроллера
#ifndef F_CPU
#define F_CPU 8000000
#endif

//определяем тип клавиатуры
//если закомментировать эту строчку
//будет задана клавиатура 3х4
//#define KEYBOARD_4X4

//используется ли общая шина
//если закомментировать - то состояния портов
//не будут сохраняться перед опросом клавиатуры
//и восстанавливаться после
//#define COMMON_BUS

//порт, к которому подкл. строки
#define PORTX_ROW PORTD
#define PINX_ROW  PIND
#define DDRX_ROW  DDRD

//порт, к которому подкл. столбцы
#define PORTX_COL PORTD
#define PINX_COL  PIND
#define DDRX_COL  DDRD

//выводы мк, к которым подключены 
//строки матричной клавиатуры
#define PIN_ROW1 4
#define PIN_ROW2 5
#define PIN_ROW3 6
#define PIN_ROW4 7

//выводы мк, к которым подключены
//столбцы матричной клавиатуры
#define PIN_COL1 0
#define PIN_COL2 1
#define PIN_COL3 2
#define PIN_COL4 3


//коды кнопок
#define EVENT_NULL 0
#define EVENT_KEY0 0x3f
#define EVENT_KEY1 0x06
#define EVENT_KEY2 0x5b
#define EVENT_KEY3 0x4f
#define EVENT_KEY4 0x66
#define EVENT_KEY5 0x6d
#define EVENT_KEY6 0x7d
#define EVENT_KEY7 0x07
#define EVENT_KEY8 0x7f
#define EVENT_KEY9 0x6f
#define EVENT_KEYA 0x62
#define EVENT_KEYB 0x62
#define EVENT_KEYC 0x62
#define EVENT_KEYD 0x62
#define EVENT_KEYZ 0x20
#define EVENT_KEYR 0x02

//пользовательские функции******************************************************

void KEYB_Init(void);                  //инициализация
void KEYB_ScanKeyboard(void);          //сканирование клавиатуры
unsigned char KEYB_GetKey(void);       //взять код нажатой кнопки

#endif //KEYBOARD_H