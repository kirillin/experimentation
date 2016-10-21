#include "keyboard.h"

//������ ������� ��������� ��������
unsigned char keyState;
//������ ��� ������� ������
unsigned char keyCode;
//������ ���������� �������� ������� ������
unsigned char keyValue;
//�������� ���������� - ���������������, ���� ������ ������������
unsigned char keyDown;
//�������� ���������� -  ���������������, ����� ������ ����� ������
unsigned char keyNew;

//������� �������������
__flash unsigned char keyTable[][2] = { 
{ 0x11, '1'},
{ 0x12, '2'},
{ 0x14, '3'},
{ 0x21, '4'},
{ 0x22, '5'},
{ 0x24, '6'},
{ 0x41, '7'},
{ 0x42, '8'},
{ 0x44, '9'},
{ 0x81, '*'},
{ 0x82, '0'},
{ 0x84, '#'}
};

//��������� ������� ������������ ���������
unsigned char AnyKey(void);
unsigned char SameKey(void);
void ScanKey(void);
unsigned char FindKey(void);
void ClearKey(void);

//������������� ������, ��������� ����������
void InitKeyboard(void)
{
  DDRD |= 0xf0;
  PORTD &= 0x0f;
  DDRC &= ~0x07;
  PORTC &= ~0x07;
  
  keyState = 0;
  keyCode = 0;
  keyValue = 0;
  keyDown = 0;
  keyNew = 0;
}

//������� ����������� ����� ����������, ������ �� ��������
// � ������������� ������� ������
void ScanKeyboard(void)
{
   switch (keyState){
     case 0: 
       if (AnyKey()) {
         ScanKey();
         keyState = 1;
       }
       break;

     case 1: 
       if (SameKey()) {
           FindKey();
           keyState = 2;
       }
       else keyState = 0;
       break;
     
     case 2: 
        if (SameKey()){}
        else keyState = 3;
        break;
    
     case 3: 
       if (SameKey()) {
         keyState = 2;
       }
       else {
         ClearKey();
         keyState = 0;
       }
       break;
     
     default:
        break;
   }
   
}

//���������� true ���� �����-������ ������ ������
unsigned char AnyKey(void) 
{
  PORTD |= 0xf0;
  return (PINC & 0x07);
}

// ���������� true ���� ������������ �� �� ������
//��� � � ���������� ����� ������
unsigned char SameKey(void) 
{
  PORTD = (PORTD & 0x0f) | ( keyCode & 0xf0);
  return ((PINC & keyCode) & 0x07);
}

//���������� ������ ������� �� ������
//��������� ��� ������� ������
void ScanKey(void) 
{
  unsigned char activeRow = (1<<4);
  while (activeRow) {
    PORTD = (PORTD & 0x0f)|activeRow;
    if (PINC & 0x07) {
      keyCode = (PINC & 0x07);
      keyCode |= (PORTD & 0xf0);
    }
    activeRow <<= 1;
  }
}

// ����������� ��� ������ � ��������������� ������
// ������������ �����
unsigned char FindKey(void) 
{
  unsigned char index;
  for (index = 0; index < 12; index++) {
    if (keyTable [index][0] == keyCode) {
      keyValue = keyTable [index][1];
      keyDown = 1;
      keyNew = 1;
      return 1;
    }
  }
  return 0;
}

//���������� ���� 
void ClearKey(void) 
{
  keyDown = 0;
}

//���� ������������� ������� ������
//���������� �� ���
unsigned char GetKey(void)
{ 
  if (keyNew){
    keyNew = 0;
    return keyValue;
  }
  else 
    return 0;
}