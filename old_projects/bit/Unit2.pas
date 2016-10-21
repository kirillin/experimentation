unit Unit2;

interface

uses
  Winapi.Windows, Winapi.Messages, System.SysUtils, System.Variants, System.Classes, Vcl.Graphics,
  Vcl.Controls, Vcl.Forms, Vcl.Dialogs, Vcl.Menus, Unit1, Unit3;

type
  TForm2 = class(TForm)
    MainMenu1: TMainMenu;
    File1: TMenuItem;
    Exit1: TMenuItem;
    Users1: TMenuItem;
    New1: TMenuItem;
    Logout1: TMenuItem;
    procedure Exit1Click(Sender: TObject);
    procedure Logout1Click(Sender: TObject);
    procedure New1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form2: TForm2;

implementation

{$R *.dfm}

procedure TForm2.Exit1Click(Sender: TObject);
begin
  Form2.Close;
end;

procedure TForm2.Logout1Click(Sender: TObject);
begin
  Form3.Show;
  Form2.Visible := false;
end;

procedure TForm2.New1Click(Sender: TObject);
begin
  Form1.Show;
  //Form2.Visible := false;
end;

end.
