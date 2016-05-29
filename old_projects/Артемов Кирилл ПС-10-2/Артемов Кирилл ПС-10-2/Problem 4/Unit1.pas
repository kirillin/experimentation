{
  The author is Artemov Kirill
  Problem 4. Funny math
  KSTU INTER 2014
}
unit Unit1;

interface

uses
  Winapi.Windows, Winapi.Messages, System.SysUtils, System.Variants, System.Classes, Vcl.Graphics,
  Vcl.Controls, Vcl.Forms, Vcl.Dialogs, Vcl.StdCtrls;

type
  TForm1 = class(TForm)
    Edit1: TEdit;
    Edit2: TEdit;
    Label1: TLabel;
    Label2: TLabel;
    Label3: TLabel;
    Button1: TButton;
    Label4: TLabel;
    Label5: TLabel;
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

procedure TForm1.Button1Click(Sender: TObject);
var
  n, k, position: longint;
  temp, sN, sK, sI: string;
  i: Integer;
  j: Integer;
begin
  sN := Form1.Edit1.Text;
  sK := Form1.Edit2.Text;
  if ((sN <> '') and (sK <> '')) then
  begin
    n := strToInt(sN);
    position := 0;
    for i := 1 to n do
    begin
      if (intToStr(i) <= sK) then inc(position);
    end;
    Form1.Label5.Caption := intToStr(position);
  end else ShowMessage('¬ведите в пол€ число!');
end;

end.
