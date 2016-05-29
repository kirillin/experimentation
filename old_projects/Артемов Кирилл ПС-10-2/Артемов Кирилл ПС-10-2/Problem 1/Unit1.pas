unit Unit1;

interface

uses
  Winapi.Windows, Winapi.Messages, System.SysUtils, System.Variants, System.Classes, Vcl.Graphics,
  Vcl.Controls, Vcl.Forms, Vcl.Dialogs, Vcl.StdCtrls;

type
  TForm1 = class(TForm)
    Label1: TLabel;
    Edit1: TEdit;
    Label2: TLabel;
    Button1: TButton;
    Label3: TLabel;
    procedure Button1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form1: TForm1;

implementation

{$R *.dfm}

{
  Function for detecting whether a string is a palindrome
}
function isPalindrome(number: string): boolean;
var
  sNumber: string;
  lengthNumber, halfLengthNumber: integer;
  i: integer;
  flag: boolean;
begin
  flag := true;
//  sNumber := intToStr(number);
  sNumber := number;
  // quantity digits in number
  lengthNumber := length(sNumber);
  // half length of number for checking process
  halfLengthNumber := lengthNumber div 2;
  for i := 1 to halfLengthNumber do
  begin
    if (sNumber[i] <> sNumber[lengthNumber - i + 1]) then
    begin
      flag := false;
      break;
    end;
  end;
  isPalindrome := flag;
end;

{
  Function for resersing string
}
function reverse(number: string): string;
var
  sNumber: string;
  temp: char;
  lengthNumber, halfLengthNumber: integer;
  i: integer;
begin
  //  sNumber := intToStr(number);
  sNumber := number;
  // quantity digits in number
  lengthNumber := length(sNumber);
  halfLengthNumber := lengthNumber div 2;
  temp := '0';
  // swapping symbols in string
  for i := 1 to halfLengthNumber do
  begin
    temp := sNumber[i];
    sNumber[i] := sNumber[lengthNumber - i + 1];
    sNumber[lengthNumber - i + 1] := temp;
  end;
  reverse := sNumber;
end;

{
  Function for summation long numbers
}
function longPlus(longA: string; longB: string):string;
var
  len, c, i: longint;
  a, b: array[1..100] of longint;
  res: string;
begin
  c := 0;
  // split symbols of strings to arrays
  len := length(LongA);
  for i := 1 to len do
    a[len - i + 1] := Ord(LongA[i])-48;
  len := length(longB);
  for i := 1 to len do
    b[len - i + 1] := Ord(longB[i])-48;
  // search number for max length
  if (length(longA) > length(longB)) then
    len := length(longA)
  else
    len := length(longB);
  // summation and transfer of tens
  for i := 1 to len do
  begin
    c := c + a[i] + b[i];
    a[i] := c mod 10;
    c := c div 10;
  end;
  // checking for the need to add to the descharge
  if (c > 0) then
  begin
    len := len + 1;
    a[len] := c;
  end;
  // write result number to string
  res := '';
  for i := len downto 1 do
    res := res + IntToStr(a[i]);
  longPlus := res;
end;

{
  Version for simple summation
  Is not work for some numbers(for exemple 573)
}
function simpleVersion(n: longint): string;
const
  // minimum necessary quantity operations
  OPS = 100;
var
  operations: longint;
  number: string;
begin
      operations := 0;
      // if "n" is not palindrom and program  not yet perform OPS operations
      while (not isPalindrome(intToStr(n))) do
      begin
        if (operations = OPS) then
        begin
          operations := -1;
          break;
        end;
        n := n + strToInt(reverse(intToStr(n)));
        inc(operations);
      end;
  simpleVersion := intToStr(operations);
end;

{
  Versia that works across the all range from 10 to 10000
}
function longVersion(n: longint): string;
const
  // minimum necessary quantity operations
  OPS = 100;
var
  operations: longint;
  sNumber: string;
begin
      sNumber := intToStr(n);
      operations := 0;
      // if "n" is not palindrom and program  not yet perform OPS operations
      while (not isPalindrome(sNumber)) do
      begin
        if (operations = OPS) then
        begin
          operations := -1;
          break;
        end;
          sNumber := longPlus(sNumber,reverse(sNumber));
          inc(operations);
      end;
  longVersion := intToStr(operations);
end;

{
  main programm
}
procedure TForm1.Button1Click(Sender: TObject);
var
  n: longint;
  operations, number: string;
begin
  number := Form1.Edit1.Text;
  if ((number <> '')) then
  begin
    if (length(number) <= 5) then
    begin
      n := strToInt(Form1.Edit1.Text);
  //  operations := simpleVersion(n);
      operations := longVersion(n);
      Form1.Label3.Caption := 'Ответ: ' + operations;
    end else ShowMessage('Вы ввели слишком большое число!');
  end else ShowMessage('Введите в поле число!');
end;

end.
