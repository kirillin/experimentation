//***************************************************************************
//
//  Author(s)...: Pashgan    http://ChipEnable.Ru   
//
//  Target(s)...: ATMega8
//
//  Compiler....: IAR 5.11A
//
//  Description.: Опрос матричной клавиатуры. Использование конечного автомата.
//
//  Data........: 06.03.10 
//
//***************************************************************************
#ifndef KEYBOARD_H
#define KEYBOARD_H

#include <ioavr.h>

//инициализация портов и внутренних переменных
void InitKeyboard(void);

//сканирование клавиатуры
void ScanKeyboard(void);

//возвращает код нажатой кнопки
unsigned char GetKey(void);

#endif //KEYBOARD_H